import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from "../../components/order-item/order-item";
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-profile-orders-delivered',
  imports: [CommonModule, OrderItem],
  templateUrl: './profile-orders-delivered.html',
  styleUrl: './profile-orders-delivered.css'
})
export class ProfileOrdersDelivered {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef){}

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

  getDeliveredPurchase(){
    this.request.executeRequestGET('account/getDeliveredPurchase').subscribe({
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
    this.getDeliveredPurchase();
  }
}
