import { Component, Input } from '@angular/core';
import { MiniCard } from '../mini-card/mini-card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MiniCard, CommonModule],
  templateUrl: './product-card.html',
  styleUrls: ['./product-card.css']
})
export class ProductCard {
   @Input() titulo?: string;
   @Input() srcImages?: string;
   @Input() miniCards?: any;
}
