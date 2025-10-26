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
    {src: "assets/banners/Novo site de vendas.png"},
    {src: "assets/banners/rapido e facil.png"},
    {src: "assets/banners/banner3.png"},
  ]

  cards = [
    { titulo: "Categorias!",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/categories/categ-acessorios.png",
         extclass: "category-class"
        },
        {src: "assets/images/categories/categ-camisas.png",
         extclass: "category-class"
        },
        {src: "assets/images/categories/categ-camisetas.png",
         extclass: "category-class"
        },
        {src: "assets/images/categories/categ-tenis.png",
         extclass: "category-class"
        },
        {src: "assets/images/categories/categ-moletons.png",
         extclass: "category-class"
        }
      ]
     },
    { titulo: "Em ofertas!",
      src: "assets/images/produto-teste.png",
      miniCard: [
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "0,99",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class"
        },
        {src: "assets/images/produto-teste.png",
         titulo: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class"
        }
      ]
     }
  ];
}
