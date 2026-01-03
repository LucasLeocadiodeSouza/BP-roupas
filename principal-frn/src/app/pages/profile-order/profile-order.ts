import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { OrderItem } from "../../components/order-item/order-item";
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-profile-order',
  imports: [OrderItem, CommonModule],
  templateUrl: './profile-order.html',
  styleUrl: './profile-order.css'
})
export class ProfileOrder {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  orderproducts: {
    img:      string;
    currency: string;
    price:    string;
    update:   boolean;
    href:     string;
    title:    string;
  }[] = [];

//[href]="orderproduct.href"
//[view]="orderproduct.src"
//[title]="orderproduct.title"
//[quantity]="orderproduct.quantity"
//[currency]="orderproduct.currency"
//[price]="orderproduct.price"
//[size]="orderproduct.size"
//[color]="orderproduct.color"
//[removeItem]="true"
//[product]="{product_id: orderproduct.product_id, size_id: orderproduct.size_id, color_id: orderproduct.color_id}"

  getPreparingPurchase(){
    this.request.executeRequestGET('account/getPreparingPurchase').subscribe({
      next: (response) => {
        if (!response) return;

        console.log(response);

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.getPreparingPurchase();
  }
}
