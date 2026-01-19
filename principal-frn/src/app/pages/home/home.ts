import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute } from '@angular/router';
import { ProductList } from '../../components/product-list/product-list';
import { CommonModule } from '@angular/common';
import { ProductCardRow } from "../../components/product-card-row/product-card-row";
import { Banner } from "../../components/banner-fit/banner-fit";

@Component({
  selector: 'app-home',
  imports: [ProductList, CommonModule, ProductCardRow, Banner],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  cards: {title?: string,
          extclass: string,
          src?: string,
          miniCard: {src: string, title?: string, price?: string, currency?: string, extclass: string, href: string }[]
        }[] = [];

  highlightCards: { title: string, src: string, miniCard: {src: string, title: string, price: string, currency: string, extclass: string, href: string }[] }[] = [];

  banners = [
    { src: "assets/banners/Novo site de vendas.png",
      height: "600px",
      width: "1190px"
    },
    { src: "assets/banners/rapido e facil.png",
      height: "600px",
      width: "1190px"
    },
    { src: "assets/banners/banner3.png",
      height: "600px",
      width: "1190px"
    }
  ]

  LoadHighlightCardsProducts(titlecard: string, path: string){
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
              currency: "R$",
              extclass: "product-list-minicard-height",
              href:     `/product?id=${card.product_id}`
          }));

          this.highlightCards = [...this.highlightCards, {src: "", title: titlecard, miniCard: cardsFormat }];
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
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
              href:     `/product?id=${card.product_id}`
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

  loadActivesCategories(){
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
              extclass: "category-class",
              href: `/products-list?category_id=${category.id}`
          }));

          this.cards = [...this.cards, {extclass: "container-category", title: "Categorias", miniCard: categformat }];
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit() {
    this.loadActivesCategories();
    this.LoadCardRowProducts("Produtos mais vendidos!", "getBestSellingProductsCartForProdRow");
    this.LoadCardRowProducts("Ultimos lançamentos!", "getNewDiscovery");
    this.LoadHighlightCardsProducts("Novas descobertas","getNewDiscovery");
    this.LoadHighlightCardsProducts("Melhores ofertas avaliadas","getBestRatedDeals");
    this.LoadHighlightCardsProducts("Recomendações da casa","getHouseRecommendations");
  }
}
