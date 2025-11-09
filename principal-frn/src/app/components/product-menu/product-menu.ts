import { Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCardRow } from "../product-card-row/product-card-row";
import { MiniCard } from "../mini-card/mini-card";

@Component({
  selector: 'app-product-menu',
  imports: [ProductCardRow, CommonModule, MiniCard],
  templateUrl: './product-menu.html',
  styleUrl: './product-menu.css'
})
export class ProductMenu {
  @Input() cards: any = [];
  @Input() itens: any = [];
  @Input() title: any;

  backToStart() {
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }
}
