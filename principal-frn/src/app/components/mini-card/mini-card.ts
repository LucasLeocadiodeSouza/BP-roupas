import { Component, input, Input } from '@angular/core';

@Component({
  selector: 'app-mini-card',
  imports: [],
  templateUrl: './mini-card.html',
  styleUrl: './mini-card.css'
})
export class MiniCard {
  @Input() srcImages: any;
  @Input() titulo: any;
  @Input() price: any;
  @Input() currency: any;
  @Input() extclass: any;
}
