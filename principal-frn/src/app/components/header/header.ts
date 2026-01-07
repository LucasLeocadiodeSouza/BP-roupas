import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute } from '@angular/router';
import { HeaderCategories } from '../header-categories/header-categories';
import { TextSugestion } from "../text-sugestion/text-sugestion";

@Component({
  selector: 'app-header',
  imports: [HeaderCategories, TextSugestion, CommonModule, FormsModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  isCategoriesVisible = false;

  categories: {id: number, name: string, href: string, subcateg: {name: string, href: string, src: string}[]}[] = [];

  textInput: string = "";
  textInputFocus: boolean = false;
  textInputHovering: boolean = false;

  sugestions: {
    href:  string;
    image: string,
    title: string,
  }[] = [];

  ngOnInit() {
    this.loadActiveSubcategories();
  }

  changeTextInputFocus(focus: boolean){
    if(!focus){
      setTimeout(() => {
        this.textInputFocus = false;
        this.cdRef.detectChanges();
      }, 100);
    }else this.textInputFocus = focus;
  }

  changeTextinput(event: any) {
    this.textInput = event.target.value;
    this.searchProductsByTextInput();
  }


  searchProductsByTextInput(){
   this.request.executeRequestGET('api/searchProductsSugestionsByTextInput', {text: this.textInput}).subscribe({
     next: (response: {product_id: number; name: string; srcimage: string;}[]) => {

      this.sugestions = response.map(info => ({
        ...this.sugestions,
        href:   "/product?id=" + info.product_id,
        image: "http://localhost:8080/api/product/" + info.srcimage,
        title: info.name
      }));

      this.cdRef.detectChanges();
     },
     error: (error) => {
       console.error('Erro:', error);
     }
   });
  }

  goToSearch() {
    window.location.href = `http://localhost:4200/products-list?search=${this.textInput}`;
  }

  loadActiveSubcategories(){
    this.request.executeRequestGET('api/findAllActivesSubcategories').subscribe({
      next: (response) => {
        var subcategories: { category_id:      number;
                             subcategory_seq:  number;
                             category_name:    string;
                             category_img:     string;
                             subcategory_name: string;
                             subcategory_img:  string}[] = [];

        subcategories = response;

        var currentCateg: number = 0;
        subcategories.forEach(subcategory1 => {
          const category_id = subcategory1.category_id;

          if(currentCateg == category_id && currentCateg != 0) return;

          var subcategformat: {name: string, href: string, src: string}[] = [];

          subcategories.forEach(subcategory2 => {
            if(subcategory2.category_id == category_id){
              subcategformat = [...subcategformat, {src: "http://localhost:8080/api/subcategoryImg/" + subcategory2.subcategory_img,
                                                    href: `/products-list?category_id=${category_id}&subcategory_id=${subcategory2.subcategory_seq}`,
                                                    name: "",}];
            }
          });

          this.categories = [...this.categories, {id: category_id,
                                                  name: subcategory1.category_name,
                                                  href: `http://localhost:4200/products-list?category_id=${category_id}`,
                                                  subcateg: subcategformat }];

          currentCateg = category_id;
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  showOverlay() { this.isCategoriesVisible = true; }
  hideOverlay() { this.isCategoriesVisible = false; }
}
