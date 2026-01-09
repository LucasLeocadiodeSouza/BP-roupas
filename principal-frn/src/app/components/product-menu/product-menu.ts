import { ChangeDetectorRef, Component, ElementRef, inject, Input, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCardRow } from "../product-card-row/product-card-row";
import { MiniCard } from "../mini-card/mini-card";
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-menu',
  imports: [ProductCardRow, CommonModule, MiniCard],
  templateUrl: './product-menu.html',
  styleUrl: './product-menu.css'
})
export class ProductMenu {
  private request = inject(RequestForm);

  @Input() cards:          any = [];
  @Input() title:          any;
  @Input() path:           string = "getProductCardForProductMenu"
  @Input() category_id:    number = 0;
  @Input() subcategory_id: number = 0;

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  productCardList: { src: string;
                     title: string;
                     price: string;
                     currency: string;
                     fullinfo: boolean;
                     extclass: string;
                     href: string;
                     score: number;
                     }[] = [];

  endPage:    boolean = false;
  pageNumber: number  = -1;

  loadProductsList(){
    var paramsurl   = this.route.snapshot.root.queryParams;
    this.pageNumber = this.pageNumber + 1;

    const params = {
      ...paramsurl,
      page: this.pageNumber,
      category_id: this.category_id,
      subcategory_id: this.subcategory_id
    }

    this.request.executeRequestGET(`api/${this.path}`, params).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           string;
                     srcimage:        string;
                     category_id:     string;
                     subcategory_seq: string;
                     avarage_rating:  number;
                     total_comments:  number;
                   }[] = [];

        cards = response;

        if(cards.length == 0) this.endPage = true;

        const newCards = cards.map(card => ({
            src:      "http://localhost:8080/api/product/product_" + card.product_id + "_1" + card.srcimage.substring(card.srcimage.lastIndexOf(".")),
            title:    card.name,
            price:    card.price,
            fullinfo: true,
            currency: "R$",
            extclass: "w100 line-clamp2",
            href:     `/product?id=${card.product_id}`,
            score:    Math.floor(card.avarage_rating)
        }));

        this.productCardList = [...this.productCardList, ...newCards];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  backToStart() {
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  ngOnInit() {
    this.loadProductsList();
  }
}
