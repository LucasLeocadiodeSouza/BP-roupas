import { CommonModule } from '@angular/common';
import { Component, Input, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-banner-fit',
  imports: [CommonModule],
  templateUrl: './banner-fit.html',
  styleUrl: './banner-fit.css'
})
export class Banner {

  @Input() banners: any[] = [];

  @ViewChild('slider', { static: true }) slider!: ElementRef<HTMLDivElement>;

  currentIndex = 0;

  next(): void {
    const sliderEl = this.slider.nativeElement;
    const slideWidth = sliderEl.clientWidth;

    this.currentIndex++;

    if (this.currentIndex >= this.banners.length) {
      this.currentIndex = 0;
    }

    sliderEl.scrollTo({
      left: slideWidth * this.currentIndex,
      behavior: 'smooth'
    });
  }

  prev(): void {
    const sliderEl = this.slider.nativeElement;
    const slideWidth = sliderEl.clientWidth;

    this.currentIndex--;

    if (this.currentIndex < 0) {
      this.currentIndex = this.banners.length - 1;
    }

    sliderEl.scrollTo({
      left: slideWidth * this.currentIndex,
      behavior: 'smooth'
    });
  }
}
