import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { StarRating } from "../../components/star-rating/star-rating";
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-insert-comment',
  imports: [CommonModule, StarRating],
  templateUrl: './insert-comment.html',
  styleUrl: './insert-comment.css'
})
export class InsertComment {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  product_id:   number = 0;
  product_name: string = "";
  product_img:  string = "";
  rating:       number = 0;

  product_description: string = "";

  texterro: string = "";

  changeTextArea(event: any) {
    this.product_description = event.target.value;
  }

  loadProductsInformation(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getProductInformation', param).subscribe({
      next: (response) => {
        var prod: { product_id:      number;
                    name:            string;
                    description:     string;
                    price:           string;
                    srcimage:        string;
                    active:          boolean;
                    category_id:     string;
                    subcategory_seq: string;
                   }[] = [];

        prod = response;

        if(prod.length == 0) console.error('Erro:', "Nao encontrado informacao para o produto");

        this.product_id   = prod[0].product_id;
        this.product_name = prod[0].name;
        this.product_img  = "http://localhost:8080/api/product/" + prod[0].srcimage;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  sendReview(){
    this.request.executeRequestPOST('api/sendReviewComment', null, {id: this.product_id, description: this.product_description, rating: this.rating}).subscribe({
      next: (response) => {
        window.history.back();
      },
      error: (error) => {
        console.error('Erro:', error);

        this.texterro = error.error;
      }
    });

    this.cdRef.detectChanges();
  }

  getValue(value: number) {
    this.rating = value;
  }

  ngOnInit() {
    this.loadProductsInformation();
  }
}
