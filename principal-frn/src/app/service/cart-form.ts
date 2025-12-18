import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RequestForm } from './request-form';

@Injectable({
  providedIn: 'root'
})
export class CartForm {
  private request = inject(RequestForm);

  private cartItemsSubject = new BehaviorSubject<any[]>([]);
  cartItems$ = this.cartItemsSubject.asObservable();

  constructor(private http: HttpClient) {}

  cartItem: { product_id: number, size_id: number, color_id: number, quantity: number }[] = [];

  loadCart() {
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) return;

      this.request.executeRequestGET('account/getCartByUserAccount').subscribe(items => {
        var cards: { useraccount_id: number,
                     product_id:     number,
                     name:           string,
                     price:          number,
                     size_id:        number,
                     size:           string,
                     color_id:       number,
                     color:          string,
                     quantity:       number,
                     image:          string
                   }[] = [];

        cards = items;

        const newCards = cards.map(cart => ({
          src:        "http://localhost:8080/api/product/product_" + cart.product_id + "_1" + cart.image.substring(cart.image.lastIndexOf(".")),
          title:      cart.name,
          price:      cart.price,
          currency:   "R$",
          extclass:   "minicard-cartform",
          href:       "http://localhost:4200/product?id=" + cart.product_id,
          product_id: cart.product_id,
          size_id:    cart.size_id,
          size:       cart.size,
          color_id:   cart.color_id,
          color:      cart.color,
          quantity:   cart.quantity
        }));

        this.cartItemsSubject.next(newCards);
      });
    });
  }

  registerProductFromCart(item: { product_id: number, size_id: number, color_id: number, quantity: number }){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) window.open('/insert/login', '_self');

      this.request.executeRequestPOST('account/registerProductFromCart', item ).subscribe({
        next: (response) => {
          this.loadCart();
        }
      });
    });

  }

  removeFromCart(item: { product_id: number, size_id: number, color_id: number, quantity: number }) {
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) window.open('/insert/login', '_self');

      this.request.executeRequestPOST(`account/removeProductFromCart`, item).subscribe(() => {
        this.loadCart();
      });
    });

  }

  ItemsSum(){
    let sum = 0;

    this.cartItemsSubject.subscribe((items: { src: string, title: string, price: number, currency: string, extclass: string, href: string }[]) => {
      items.forEach(item => {
        sum += item.price;
      });

    });
    return Number(sum.toFixed(2));
  }
}
