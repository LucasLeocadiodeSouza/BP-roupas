import { Component, Input, ViewChild, ViewContainerRef } from '@angular/core';
import { MiniCard } from "../mini-card/mini-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shopping-list',
  imports: [MiniCard, CommonModule],
  templateUrl: './shopping-list.html',
  styleUrl: './shopping-list.css'
})
export class ShoppingList {
  @Input() currency: any;
  @Input() price: any;

  vcr!: ViewContainerRef;
  @ViewChild('produtsLists', { read: ViewContainerRef }) produtsLists!: ViewContainerRef;

  miniCards = [
      {src: "assets/images/produto-teste.png",
        titulo: "Fone de Ouvido Headset",
        price: "25,99",
        currency: "R$",
        extclass: "h110px"
      },
      {src: "assets/images/produto-teste.png",
        titulo: "Fone de Ouvido Headset",
        price: "257,90",
        currency: "R$",
        extclass: "h110px"
      },
      {src: "assets/images/produto-teste.png",
        titulo: "Fone de Ouvido Headset",
        price: "785,25",
        currency: "R$",
        extclass: "h110px"
      },
      {src: "assets/images/produto-teste.png",
        titulo: "Fone de Ouvido Headset",
        price: "1.500,25",
        currency: "R$",
        extclass: "h110px"
      }
    ];

    deleteProductInCart(element: HTMLElement): void{
      element.remove();
      
      const containerProduct = this.produtsLists.element.nativeElement;

      if(containerProduct.childNodes.length <= 1) containerProduct.remove();
    }
}
