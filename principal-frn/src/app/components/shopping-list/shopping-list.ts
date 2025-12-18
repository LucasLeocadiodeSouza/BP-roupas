import { Component, Input, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { MiniCard } from "../mini-card/mini-card";
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { CartForm } from '../../service/cart-form';

@Component({
  selector: 'app-shopping-list',
  imports: [MiniCard, CommonModule],
  templateUrl: './shopping-list.html',
  styleUrl: './shopping-list.css'
})
export class ShoppingList implements OnInit {
  constructor(private cartForm: CartForm) {}

  cartItems$!: Observable<any[]>;

  @Input() currency: string = "R$";
  @Input() price: number = 0.00;

  vcr!: ViewContainerRef;
  @ViewChild('produtsLists', { read: ViewContainerRef }) produtsLists!: ViewContainerRef;

  deleteProductInCart(element: HTMLElement): void{
    element.remove();

    const containerProduct = this.produtsLists.element.nativeElement;

    if(containerProduct.childNodes.length <= 1) containerProduct.remove();
  }

  getPriceSum(){
    return this.cartForm.ItemsSum();
  }

  ThisEmptycart(){
    let thisEmptycart = false;

    this.cartForm.cartItems$.subscribe((cart) => { thisEmptycart = cart.length == 0; });

    return thisEmptycart;
  }

  removeFromCart(product_id: number, size_id: number, color_id: number, quantity: number) {
    this.cartForm.removeFromCart({product_id: product_id, size_id: size_id, color_id: color_id, quantity: quantity});
  }

  ngOnInit(): void {
    this.cartItems$ = this.cartForm.cartItems$;
    this.cartForm.loadCart();

  }
}
