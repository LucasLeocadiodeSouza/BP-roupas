import { Component, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShoppingList } from "../../components/shopping-list/shopping-list";
import { Footer } from "../../components/footer/footer";
import { RouterOutlet } from "@angular/router";
import { Header } from "../../components/header/header";
import { Observable } from 'rxjs';
import { CartForm } from '../../service/cart-form';

@Component({
  selector: 'app-main',
  imports: [ShoppingList, Footer, RouterOutlet, Header, CommonModule],
  templateUrl: './main.html',
  styleUrl: './main.css'
})
export class Main {
  constructor(private cartForm: CartForm, private el: ElementRef) {}

  cartItems$!: Observable<any[]>;

  ThisEmptycart(){
    let thisEmptycart = false;

    this.cartForm.cartItems$.subscribe((cart) => { thisEmptycart = cart.length == 0; });

    this.setWidthShop(thisEmptycart?"0px":"260px");

    return thisEmptycart;
  }

  setWidthShop(width: string){
    this.el.nativeElement.style.setProperty('--layout-main-w-shop', width);
  }
}
