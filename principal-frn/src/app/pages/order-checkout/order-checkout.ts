import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../../components/order-item/order-item';
import { CartForm } from '../../service/cart-form';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-checkout',
  imports: [OrderItem, CommonModule],
  templateUrl: './order-checkout.html',
  styleUrl: './order-checkout.css'
})
export class OrderCheckout {
  constructor(private cartForm: CartForm) {}

  cartItems$!: Observable<any[]>;

  getPriceSum(){
    return this.cartForm.ItemsSum();
  }

  getPriceDelivery(){
    return this.cartForm.ItemsSum();
  }

  getPriceSumPlusDelivery(){
    return this.cartForm.ItemsSum();
  }

  ngOnInit(): void {
    this.cartItems$ = this.cartForm.cartItems$;
    this.cartForm.loadCart();
  }

  ThisEmptyCart(){
    let thisEmptycart = false;

    this.cartForm.cartItems$.subscribe((cart) => { thisEmptycart = cart.length == 0; });

    return thisEmptycart;
  }
}
