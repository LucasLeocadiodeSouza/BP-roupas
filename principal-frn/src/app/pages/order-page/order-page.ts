import { Component, inject, Input } from '@angular/core';
import { OrderItem } from "../../components/order-item/order-item";
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-order-page',
  imports: [CommonModule, OrderItem],
  templateUrl: './order-page.html',
  styleUrl: './order-page.css'
})
export class OrderPage {
  @Input() orderproducts: {
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

  @Input() status:        number = 0;
  @Input() subtitle:      string = "";
}
