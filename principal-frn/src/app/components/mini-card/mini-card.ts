import { Component, input, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mini-card',
  imports: [CommonModule],
  templateUrl: './mini-card.html',
  styleUrl: './mini-card.css'
})
export class MiniCard {
  @Input() srcImages: any;
  @Input() title: any;
  @Input() price: any;
  @Input() currency: any;
  @Input() extclass: any;
  @Input() fullinfo!: boolean;
  @Input() href: any;

  score = "12mil";
}
