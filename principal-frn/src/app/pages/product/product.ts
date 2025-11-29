import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Banner } from "../../components/banner/banner";
import { MiniCard } from "../../components/mini-card/mini-card";

@Component({
  selector: 'app-product',
  imports: [Banner, CommonModule, MiniCard],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product {
  //@Input() category: any;
  //@Input() product: any;
  //@Input() subcategory: any;

  category    = "Camiseta";
  subcategory = "Oversize";
  product     = "Camisa oversizer com estampa";

  selectEspecification(event: any) {
    const element = event.target.closest('.control-button');

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
  }

  banners = [
    {src: "assets/images/produto-teste.png",
     height: "600px",
     width: "495px"
    },
    {src: "assets/images/arrow-down.png",
     height: "600px",
     width: "495px"
    },
    {src: "assets/images/icon_instagram.png",
     height: "600px",
     width: "495px"
    },
    {src: "assets/images/header 2.png",
     height: "600px",
     width: "495px"
    },
    {src: "assets/images/shopp-icon.png",
     height: "600px",
     width: "495px"
    },
    {src: "assets/images/shopping-icon.png",
     height: "600px",
     width: "495px"
    },
  ]

  title       = "Camiseta Esportiva Masculina Manga Curta Dryline Elite 025392";
  description = "Camiseta Esportiva Masculina - Elite * Ideal para a prática de esporte e uso diário * Confeccionado em poliéster * Gola careca * Manga curta * Tecnologia dryline, que permite a rápida secagem da peça proporcionando maior conforto e melhor desempenho * Cores neutras que permitem combinações diversas com outras peças e acessórios Composição: 100% Poliéster Marca: Elite Ref: 25392";
  price       = "49,49";
  currency    = "R$";
  sales       = "534";
  score       = "4.5";

  itens = [
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "0,99",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "0,99",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "3.700,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "70,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "0,99",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "itens-class",
         fullinfo: true,
         href: "/product?category=1&subcategory=3"
        }
      ];
}
