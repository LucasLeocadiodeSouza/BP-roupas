import { ChangeDetectorRef, Component, ElementRef, inject, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products-list',
  imports: [CategoriesList, ProductMenu, CommonModule],
  templateUrl: './products-list.html',
  styleUrl: './products-list.css'
})
export class ProductsList {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  barContainer: any;
  titlemenu: string = "";

  cards: {title?: string,
          extclass: string,
          src?: string,
          miniCard: {src: string, title?: string, price?: string, currency?: string, extclass: string, href: string }[]
        }[] = [];

  loadActivesSubcategories(){
    const paramsurl = this.route.snapshot.root.queryParams;

    if(!paramsurl['subcategory_id']){
      this.request.executeRequestGET('api/findAllActivesSubcategories', paramsurl).subscribe({
        next: (response) => {
          var subcategories: { category_id:      number;
                               subcategory_seq:  number;
                               category_name:    string;
                               category_img:     string;
                               subcategory_name: string;
                               subcategory_img:  string}[] = [];

          subcategories = response;

          const subcategformat = subcategories.map(subcategory => ({
              src: "http://localhost:8080/api/subcategoryImg/" + subcategory.subcategory_img,
              extclass: "subcategory-class",
              href: `/products-list?category_id=${subcategory.category_id}&subcategory_id=${subcategory.subcategory_seq}`,
              title: "",
              price: "",
              currency: ""
          }));

          this.cards = [...this.cards, {extclass: "container-subcategory", title:"", src: "", miniCard: subcategformat }];

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    }
  }

  LoadCardRowProducts(titlerow: string, path: string){
    const paramsurl = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET(`api/${path}`, paramsurl).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           string;
                     srcimage:        string;
                     category_id:     string;
                     subcategory_seq: string }[] = [];

        cards = response;

        if(cards.length != 0){
          const cardsFormat = cards.map(card => ({
              src:      "http://localhost:8080/api/product/product_" + card.product_id + "_1" + card.srcimage.substring(card.srcimage.lastIndexOf(".")),
              title:    card.name,
              price:    card.price,
              fullinfo: false,
              currency: "R$",
              extclass: "product-class",
              href:     `/product?id=${card.product_id}&category_id=${card.category_id}&subcategory_id=${card.subcategory_seq}`
          }));

          this.cards = [...this.cards, {extclass: "m10", title: titlerow, miniCard: cardsFormat }];
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  loadActiveCategories(){
    this.request.executeRequestGET('api/findAllCategoriesActive').subscribe({
      next: (response) => {
        var categories: { id:     number;
                          name:   string;
                          active: boolean}[] = [];

        categories = response;

        const categformat = categories.map(category => ({
            ...category,
            href:    `/products-list?category_id=${category.id}`
        }));

        this.barContainer = [
          {
            title: "Categorias",
            buttons: categformat
          }
        ];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getTitleSearch(){
    const paramsurl = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getSubCategoryById', paramsurl).subscribe({
      next: (response) => {

        this.titlemenu = response.category_name;

        if(response.subcategory_name) this.titlemenu = this.titlemenu + " - " + response.subcategory_name;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit() {
    this.getTitleSearch();
    this.loadActiveCategories();
    this.loadActivesSubcategories();
    this.LoadCardRowProducts("Em oferta!", "getBestSellingProducts");
  }
}
