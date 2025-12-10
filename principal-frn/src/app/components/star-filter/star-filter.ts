import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StarRating } from "../star-rating/star-rating";

@Component({
  selector: 'app-star-filter',
  imports: [CommonModule, StarRating],
  templateUrl: './star-filter.html',
  styleUrl: './star-filter.css'
})
export class StarFilter {
  @Output() newValue = new EventEmitter<number>();

  setStarFilterValue(value: number) {
    this.newValue.emit(value);
  }
}
