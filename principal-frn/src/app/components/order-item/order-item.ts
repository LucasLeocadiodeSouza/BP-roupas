import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartForm } from '../../service/cart-form';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-item',
  imports: [CommonModule],
  templateUrl: './order-item.html',
  styleUrl: './order-item.css'
})
export class OrderItem {
  constructor(private cartForm: CartForm) {}

  cartItems$!: Observable<any[]>;

  @Input() href: any;
  @Input() view: any;
  @Input() title: any;
  @Input() quantity: number = 0;
  @Input() currency: any;
  @Input() price:    number = 0;
  @Input() discount: number = 0;
  @Input() size:  any;
  @Input() color: any;
  @Input() removeItem: boolean = false;

  @Input() product: {
    product_id: number;
    size_id:    number;
    color_id:   number;
  } = { product_id: 0, size_id: 0, color_id: 0 };

  getPriceFinal(){
    const realPriceXdiscount = (this.price - this.discount).toFixed(2);

    return (Number(realPriceXdiscount) * this.quantity);
  }

  removeFromCart() {
    this.cartForm.removeFromCart({product_id: this.product.product_id, size_id: this.product.size_id, color_id: this.product.color_id, quantity: this.quantity});
  }
}
