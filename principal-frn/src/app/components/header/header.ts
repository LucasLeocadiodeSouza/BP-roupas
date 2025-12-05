import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute } from '@angular/router';
import { HeaderCategories } from '../header-categories/header-categories';

@Component({
  selector: 'app-header',
  imports: [HeaderCategories],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  isCategoriesVisible = false;

  categories: {id: number, name: string, href: string, subcateg: {name: string, href: string, src: string}[]}[] = [];

  ngOnInit() {
    this.loadActiveSubcategories();
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
