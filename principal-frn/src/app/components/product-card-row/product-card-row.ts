import { Component, Input, AfterViewInit, ViewChild, ViewContainerRef } from '@angular/core';
import { MiniCard } from "../mini-card/mini-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-card-row',
  imports: [MiniCard, CommonModule],
  templateUrl: './product-card-row.html',
  styleUrl: './product-card-row.css'
})
export class ProductCardRow implements AfterViewInit   {
  @Input() title: any;
  @Input() extclass: any;
  @Input() miniCards: any;

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
    this.prevCarrTeste(this.containerProduct,containerRow);
  }

  nextButtonClick(): void{
    const containerRow = this.containerProduct.element.nativeElement;
    this.nextCarrTeste(this.containerProduct,containerRow);
  }

  isOverflowVisible(element: HTMLElement): boolean {
    return element.scrollWidth > element.clientWidth;
  }

  nextCarrTeste(row: ViewContainerRef, containerID: any): void{
    const rowDOM         = row.element.nativeElement;
    const widthContainer = rowDOM.offsetWidth;
    const nodes          = containerID.childNodes;

    const scrollPosition = this.getNextFeaturedProduct(nodes, widthContainer);

    rowDOM.scrollTo({
        left: scrollPosition,
        behavior: "smooth"
    });
  }

  prevCarrTeste(row: ViewContainerRef, containerID: any): void{
    const rowDOM         = row.element.nativeElement;
    const widthContainer = rowDOM.offsetWidth;
    const nodes          = containerID.childNodes;

    const scrollPosition = this.getPrevFeaturedProduct(nodes, widthContainer);

    rowDOM.scrollTo({
        left: scrollPosition,
        behavior: "smooth"
    });
  }

  getNextFeaturedProduct(childNodes: any[], widthContainer: any){
    let childrenwidth  = 0;
    let remainingWidth = 0;

    for(let index = 0; childNodes.length > index; index++){
      const node = childNodes[index];
      if (node.nodeType === Node.COMMENT_NODE) continue;

      childrenwidth = childrenwidth + node.offsetWidth;

      const widthseq = childrenwidth - this.beforeWidthCarrOn(childNodes);

      if( widthseq >= widthContainer &&
          remainingWidth >= widthContainer
        ) {
        const newposition = Math.floor(childrenwidth / widthContainer) * widthContainer;
        this.newCardCarrOnByWidth(childNodes, newposition, true);
        return newposition;
      }

      if(node.classList.contains("carr-on")) remainingWidth = this.remainingWidthCarrOn(childNodes);
    };

    this.addCarrOffForAllCard(childNodes);

    childNodes[0].classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
    childNodes[0].classList.add("carr-on");

    return 0;
  }

  getPrevFeaturedProduct(childNodes: any[], widthContainer: any){
    let childrenwidth = this.beforeWidthCarrOn(childNodes);

    for(let index = 0; childNodes.length > index; index++){
      const node = childNodes[index];
      if (node.nodeType === Node.COMMENT_NODE) continue;

      childrenwidth = childrenwidth - node.offsetWidth;

      if( childrenwidth >= 0) {
        const newposition = Math.floor(childrenwidth / widthContainer) * widthContainer;

        this.newCardCarrOnByWidth(childNodes, newposition, false);

        return newposition;
      }
    };

    this.addCarrOffForAllCard(childNodes);

    childNodes[childNodes.length - 2].classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
    childNodes[childNodes.length - 2].classList.add("carr-on");

    return this.widthRow(childNodes);
  }

  remainingWidthCarrOn(childNodes: any[]){
    let childrenHiddenWidth = 0;
    let carron              = false;

    childNodes.forEach((node: any) => {
      if (node.nodeType === Node.COMMENT_NODE) return;
      if(node.classList.contains("carr-on")) carron = true;
      if(carron) childrenHiddenWidth = childrenHiddenWidth + node.offsetWidth;
    })

    return childrenHiddenWidth;
  }

  beforeWidthCarrOn(childNodes: any[]){
    let childrenHiddenWidth = 0;
    let carron              = false;

    childNodes.forEach((node: any) => {
      if (node.nodeType === Node.COMMENT_NODE) return;

      if(node.classList.contains("carr-on")) carron = true;
      if(!carron) childrenHiddenWidth = childrenHiddenWidth + node.offsetWidth;
    });

    return childrenHiddenWidth;
  }

  newCardCarrOnByWidth(childNodes: any[], newWidth: any, eventnext: boolean){
    let childrenHiddenWidth = eventnext?0 : this.widthRow(childNodes);
    let cardindex = 0;

    if(eventnext){
      for(let index = 0; childNodes.length > index; index++){
        const node = childNodes[index];

        if (node.nodeType === Node.COMMENT_NODE) continue;

        if(cardindex != 0) continue;

        if(newWidth >= childrenHiddenWidth &&
           newWidth <= childrenHiddenWidth + node.offsetWidth
        ) cardindex = index;

        childrenHiddenWidth = childrenHiddenWidth + node.offsetWidth;
      }
    }else{
      for(let index = childNodes.length - 2; index > 0; index--){
        const node = childNodes[index];

        if (node.nodeType === Node.COMMENT_NODE) continue;

        if(cardindex != 0) continue;

        if(newWidth <= childrenHiddenWidth &&
           newWidth >= childrenHiddenWidth - node.offsetWidth
        ) cardindex = index;

        childrenHiddenWidth = childrenHiddenWidth - node.offsetWidth;
      }
    }

    this.addCarrOffForAllCard(childNodes);

    childNodes[cardindex].classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
    childNodes[cardindex].classList.add("carr-on");
  }

  addCarrOffForAllCard(childNodes: any[]){
    childNodes.forEach((node: any, index: any) => {
      if (node.nodeType === Node.COMMENT_NODE) return;

      node.classList.remove("carr-off", "carr-on", "show-effecty", "hidden-effecty", "dnone");
      node.classList.add("carr-off");
    });
  }

  widthRow(childNodes: any[]){
    let childrenwidth = 0;

    childNodes.forEach((node: any) => {
      if (node.nodeType === Node.COMMENT_NODE) return;

      childrenwidth = childrenwidth + node.offsetWidth;
    });

    return childrenwidth;
  }
}
