import { Component } from '@angular/core';
import { ProductCard } from "../product-card/product-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-list',
  imports: [ProductCard, CommonModule],
  templateUrl: './product-list.html',
  styleUrls: ['./product-list.css']
})
export class ProductList {

  cards = [
    { titulo: "Novas descobertas",
      src: "assets/images/produto-teste.png",
      miniCard: [
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
      ]
     },
    { titulo: "Melhores ofertas avaliadas",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        }
      ]
     },
    { titulo: "Recomendacões da casa",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "h110px"
        }
      ]
     }
  ];
}
