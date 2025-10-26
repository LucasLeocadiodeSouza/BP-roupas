import { Component, inject, Input, AfterViewInit, ViewChild, ViewContainerRef } from '@angular/core';
import { ProductCard } from "../product-card/product-card";
import { Banner } from '../banner/banner';
import { ElementsForm } from '../../service/elements-form';
import { MiniCard } from "../mini-card/mini-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-card-row',
  imports: [ProductCard, Banner, MiniCard, CommonModule],
  templateUrl: './product-card-row.html',
  styleUrl: './product-card-row.css'
})
export class ProductCardRow implements AfterViewInit   {
  @Input() titulo: any;
  @Input() miniCards: any;

  private prevCarr = inject(ElementsForm);

  vcr!: ViewContainerRef;
  @ViewChild('containerProduct', { read: ViewContainerRef }) containerProduct!: ViewContainerRef;
  @ViewChild('prevbutton', { read: ViewContainerRef }) prevbutton!: ViewContainerRef;
  @ViewChild('nextbutton', { read: ViewContainerRef }) nextbutton!: ViewContainerRef;

  ngAfterViewInit () {
    const prevbutton = this.prevbutton.element.nativeElement;
    const nextbutton = this.nextbutton.element.nativeElement;

    const container = this.containerProduct.element.nativeElement;

    if(this.isOverflowVisible(container)) {
      prevbutton.classList.remove("dnone");
      nextbutton.classList.remove("dnone");
      
      prevbutton.classList.add("dflex");
      nextbutton.classList.add("dflex");
    }else {
      prevbutton.classList.remove("dflex");
      nextbutton.classList.remove("dflex");

      prevbutton.classList.add("dnone");
      nextbutton.classList.add("dnone");
    }

  }

  prevButtonClick(): void{
    const containerRow = this.containerProduct.element.nativeElement;
    this.prevCarr.prevCarr(this.containerProduct,containerRow);
  }

  nextButtonClick(): void{
    const containerRow = this.containerProduct.element.nativeElement;
    this.prevCarr.nextCarr(this.containerProduct,containerRow);
  }

  isOverflowVisible(element: HTMLElement): boolean {
    return element.scrollWidth > element.clientWidth;
  }
}
