import { Component, Input } from '@angular/core';
import { ProductCard } from "../product-card/product-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-list',
  imports: [ProductCard, CommonModule],
  templateUrl: './product-list.html',
  styleUrls: ['./product-list.css']
})
export class ProductList {
  @Input() cards: any;
}
