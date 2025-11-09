import { Component } from '@angular/core';
import { Header } from '../../components/header/header';
import { Banner } from '../../components/banner/banner';
import { Footer } from '../../components/footer/footer';
import { ProductCard } from '../../components/product-card/product-card';
import { ProductList } from '../../components/product-list/product-list';
import { ShoppingList } from "../../components/shopping-list/shopping-list";
import { CommonModule } from '@angular/common';
import { ProductCardRow } from "../../components/product-card-row/product-card-row";

@Component({
  selector: 'app-home',
  imports: [Header, Banner, Footer, ProductCard, ProductList, ShoppingList, CommonModule, ProductCardRow],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  banners = [
    { src: "assets/banners/Novo site de vendas.png",
      height: "600px",
      width: "1190px"
    },
    { src: "assets/banners/rapido e facil.png",
      height: "600px",
      width: "1190px"
    },
    { src: "assets/banners/banner3.png",
      height: "600px",
      width: "1190px"
    }
  ]

  cards = [
    { title: "Categorias!",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/categories/categ-acessorios.png",
         extclass: "category-class",
         href: "/products-list?category=1"
        },
        {src: "assets/images/categories/categ-camisas.png",
         extclass: "category-class",
         href: "/products-list?category=2"
        },
        {src: "assets/images/categories/categ-camisetas.png",
         extclass: "category-class",
         href: "/products-list?category=3"
        },
        {src: "assets/images/categories/categ-tenis.png",
         extclass: "category-class",
         href: "/products-list?category=4"
        },
        {src: "assets/images/categories/categ-calcas.png",
         extclass: "category-class",
         href: "/products-list?category=5"
        },
        {src: "assets/images/categories/categ-moletons.png",
         extclass: "category-class",
         href: "/products-list?category=6"
        }
      ]
     },
    { title: "Em ofertas!",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "0,99",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        }
      ]
     },
     { title: "Ultimos lancamentos!",
       src: "assets/images/produto-teste.png",
       miniCard: [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        }
      ]
     }
  ];
}
