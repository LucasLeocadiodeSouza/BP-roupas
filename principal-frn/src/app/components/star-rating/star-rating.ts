import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-star-rating',
  imports: [CommonModule],
  templateUrl: './star-rating.html',
  styleUrl: './star-rating.css'
})
export class StarRating {
  @Input() name:       string  = "rate";
  @Input() rating:     number  = 0;
  @Input() starHeight: string  = "0";
  @Input() enable:     boolean = true;

  @Output() newValue = new EventEmitter<number>();

  setStarFilterValue(value: number) {
    this.newValue.emit(value);
  }
}
