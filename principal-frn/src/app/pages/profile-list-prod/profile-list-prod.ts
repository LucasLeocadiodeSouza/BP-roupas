import { ChangeDetectorRef, Component, inject, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MiniCard } from "../../components/mini-card/mini-card";
import { RequestForm } from '../../service/request-form';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-profile-list-prod',
  imports: [CommonModule, MiniCard],
  templateUrl: './profile-list-prod.html',
  styleUrl: './profile-list-prod.css'
})
export class ProfileListProd {
  private request = inject(RequestForm);
  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  listName: string = "";

  itens: { src: string;
           title: string;
           price: string;
           currency: string;
           fullinfo: boolean;
           extclass: string;
           href: string;
           score: number;
         }[] = [];

  loadAllProductsFromTheList(){
    this.request.executeRequestGET(`account/getUserListById`, this.route.snapshot.root.queryParams).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           string;
                     srcimage:        string;
                     image:           string;
                     category_id:     string;
                     subcategory_seq: string;
                     avarage_rating:  number;
                     total_comments:  number;
                   }[] = [];

        cards = response;

        const newCards = cards.map(card => ({
            src:      "http://localhost:8080/api/product/" + card.image,
            title:    card.name,
            price:    card.price,
            fullinfo: true,
            currency: "R$",
            extclass: "w100 line-clamp2",
            href:     `/product?id=${card.product_id}`,
            score:    Math.floor(card.avarage_rating)
        }));

        this.itens = [...this.itens, ...newCards];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.listName = this.route.snapshot.root.queryParams["title"];

    this.loadAllProductsFromTheList();
  }
}
