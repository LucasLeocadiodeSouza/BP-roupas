import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { RequestForm } from './../../service/request-form';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products-best-sellings',
  imports: [ProductMenu, CategoriesList],
  templateUrl: './products-best-sellings.html',
  styleUrl: './products-best-sellings.css'
})
export class ProductsBestSellings {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: Router) {}

  barContainer:   any;
  titlemenu:      string = "Mais vendidos";
  category_id:    number = 0;
  subcategory_id: number = 0;

  cards: {title?: string,
          extclass: string,
          src?: string,
          miniCard: {src: string, title?: string, price?: number, currency?: string, extclass: string, href: string; total_comments: string; discount: number; }[]
        }[] = [];

  cardcateg: {title?: string,
               extclass: string,
               src?: string,
               miniCard: {src: string, title?: string, price?: number, currency?: string, extclass: string, href: string }[]
             }[] = [];

  getParamsRequests(){
    const url = this.route.url;

    this.request.executeRequestGET('api/getParamsRequests', { url: url }).subscribe({
      next: (response : { category_id: number; subcategory_seq: number; }) => {

        this.category_id    = !response.category_id? 0 : response.category_id;
        this.subcategory_id = !response.subcategory_seq? 0 : response.subcategory_seq;

        this.cdRef.detectChanges();

        this.getActiveCategRowCard();
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

  getActiveCategRowCard(){
    this.request.executeRequestGET('api/findAllCategoriesActive', {}).subscribe({
      next: (response) => {
        var categories: { id:     number;
                          name:   string;
                          active: boolean;
                          image:  string;}[] = [];

        categories = response;

        if(categories.length != 0){
          const categformat = categories.map(category => ({
              src: "http://localhost:8080/api/categoryImg/" + category.image,
              extclass: "product-list-category-class",
              href: `/products-best-selling?category_id=${category.id}`
          }));

          this.cardcateg = [...this.cardcateg, {extclass: "container-category", title: "Procurar por Categorias", miniCard: categformat }];
        }

        this.cdRef.detectChanges();

        this.LoadCardRowProducts("Estão dando o que falar!", "getProductsWithMostCommentsForProdRow");
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.loadActiveCategories();
    this.getParamsRequests();
  }
}
