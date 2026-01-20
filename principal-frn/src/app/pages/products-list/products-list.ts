import { ChangeDetectorRef, Component, ElementRef, inject, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products-list',
  imports: [CategoriesList, ProductMenu, CommonModule],
  templateUrl: './products-list.html',
  styleUrl: './products-list.css'
})
export class ProductsList {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: Router, private activatedRoute: ActivatedRoute) {}

  barContainer:   any;
  titlemenu:      string = "";
  category_id:    number = 0;
  subcategory_id: number = 0;

  cardcateg: {title?: string,
               extclass: string,
               src?: string,
               miniCard: {src: string, title?: string, extclass: string, href: string; }[]
             }[] = [];

  cards: {title?: string,
          extclass: string,
          src?: string,
          miniCard: {src: string, title?: string, price?: number, currency?: string, extclass: string, href: string; total_comments: string; discount: number; }[]
        }[] = [];


  getParamsRequests(){
    const url = this.route.url;

    this.request.executeRequestGET('api/getParamsRequests', { url: url }).subscribe({
      next: (response : { category_id: number; category_name: string; subcategory_seq: number; subcategory_name: string; }) => {

        this.category_id    = !response.category_id? 0 : response.category_id;
        this.subcategory_id = !response.subcategory_seq? 0 : response.subcategory_seq;

        if(this.activatedRoute.snapshot.root.queryParams['search']) this.titlemenu = this.activatedRoute.snapshot.root.queryParams['search'];
        else{
          this.titlemenu = response.category_name;

          if(response.subcategory_name) this.titlemenu = this.titlemenu + " - " + response.subcategory_name;
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  loadActivesSubcategories(){
    if(this.subcategory_id == 0){
      this.request.executeRequestGET('api/findAllActivesSubcategories', { category_id: this.category_id }).subscribe({
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
          }));

          this.cardcateg = [...this.cardcateg, {extclass: "container-subcategory", title:"", src: "", miniCard: subcategformat }];

          this.LoadCardRowProducts("Em oferta!", "getProductsWithDiscountForProdRow");

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    }else this.LoadCardRowProducts("Em oferta!", "getProductsWithDiscountForProdRow");
  }

  LoadCardRowProducts(titlerow: string, path: string){
    this.request.executeRequestGET(`api/${path}`, { category_id: this.category_id, subcategory_id: this.subcategory_id }).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           number;
                     srcimage:        string;
                     category_id:     string;
                     subcategory_seq: string;
                     total_comments:  string;
                     discount:        number; }[] = [];

        cards = response;

        if(cards.length != 0){
          const cardsFormat = cards.map(card => ({
              prodId:         card.product_id,
              src:            "http://localhost:8080/api/product/product_" + card.product_id + "_1" + card.srcimage.substring(card.srcimage.lastIndexOf(".")),
              title:          card.name,
              price:          card.price,
              discount:       card.discount,
              total_comments: card.total_comments,
              fullinfo:       false,
              currency:       "R$",
              extclass:       "product-class",
              href:           `/product?id=${card.product_id}`
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

  ngOnInit() {
    this.getParamsRequests();
    this.loadActiveCategories();
    this.loadActivesSubcategories();
  }
}
