import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';
import { OrderPage } from "../order-page/order-page";

@Component({
  selector: 'app-profile-orders-shipped',
  imports: [CommonModule, OrderPage],
  templateUrl: './profile-orders-shipped.html',
  styleUrl: './profile-orders-shipped.css'
})
export class ProfileOrdersShipped {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  orderproducts: {
    href:       string;
    src:        string;
    title:      string;
    currency:   string;
    quantity:   number;
    price:      number;
    size:       string;
    color:      string;
    product_id: number;
    size_id:    number;
    color_id:   number;
  }[] = [];

  getWaitingPurchase(){
    this.request.executeRequestGET('account/getWaitingPurchase').subscribe({
      next: (response: any) => {

        const purchase : {
          useraccount_id: number;
          product_id:     number;
          name:           string;
          price:          number;
          size_id:        number;
          size:           string;
          color_id:       number;
          color:          string;
          quantity:       number;
          image:          string;
        }[] = response;

        this.orderproducts = purchase.map(info => ({
            ...this.orderproducts,
            href       : "/product?id=" + info.product_id,
            src        : "http://localhost:8080/api/product/" + info.image,
            title      : info.name,
            currency   : "R$",
            quantity   : info.quantity,
            price      : info.price,
            size       : info.size,
            color      : info.color,
            product_id : info.product_id,
            size_id    : info.size_id,
            color_id   : info.color_id
        }));

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.getWaitingPurchase();
  }
}
