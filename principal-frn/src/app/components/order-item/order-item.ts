import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-item',
  imports: [CommonModule],
  templateUrl: './order-item.html',
  styleUrl: './order-item.css'
})
export class OrderItem {
  @Input() href: any;
  @Input() view: any;
  @Input() unid: any;
  @Input() title: any;
  @Input() currency: any;
  @Input() price: any;
  @Input() update!: boolean;
}
