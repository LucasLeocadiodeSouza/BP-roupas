import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-order-item',
  imports: [],
  templateUrl: './order-item.html',
  styleUrl: './order-item.css'
})
export class OrderItem {
  @Input() view: any;
  @Input() unid: any;
  @Input() title: any;
  @Input() currency: any;
  @Input() price: any;
}
