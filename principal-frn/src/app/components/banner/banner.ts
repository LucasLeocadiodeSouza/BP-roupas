import { CommonModule } from '@angular/common';
import { Component, inject, Input, ViewChild, ViewContainerRef } from '@angular/core';
import { ElementsForm } from '../../service/elements-form';

@Component({
  selector: 'app-banner',
  imports: [CommonModule],
  templateUrl: './banner.html',
  styleUrl: './banner.css'
})
export class Banner {
  @Input() banners?: any;
  @Input() hiddenEffect: boolean = false;

  private prevCarr = inject(ElementsForm);

  vcr!: ViewContainerRef;
  @ViewChild('containerBanners', { read: ViewContainerRef }) containerBanners!: ViewContainerRef;


  prevButtonClick(): void{
      this.prevCarr.prevCarr(this.containerBanners, this.containerBanners.element.nativeElement, this.hiddenEffect);
  }

  nextButtonClick(): void{
      this.prevCarr.nextCarr(this.containerBanners, this.containerBanners.element.nativeElement, this.hiddenEffect);
  }
}
