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
    { title: "Novas descobertas",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "25,99",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "257,90",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "785,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        }
      ]
     },
    { title: "Melhores ofertas avaliadas",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        }
      ]
     },
    { title: "Recomendacões da casa",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-list-minicard-height",
         href: "/product?category=1&subcategory=3"
        }
      ]
     }
  ];
}
