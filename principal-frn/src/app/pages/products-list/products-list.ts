import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { title } from 'process';

@Component({
  selector: 'app-products-list',
  imports: [CategoriesList, ProductMenu, CommonModule],
  templateUrl: './products-list.html',
  styleUrl: './products-list.css'
})
export class ProductsList {
  titlemenu = "Camisas";

  categories = [
    {id: 1, name: "Acessórios", href: "#", subcateg: [
      {name: "Relogios", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Correntes", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Pulseiras", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Bonés", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Anéis", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
    ]},
    {id: 2, name: "Camisas", href: "#", subcateg: [
      {name: "Social",      href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Slim Fit",    href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Regular Fit", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Comfort Fit", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"}
    ]},
    {id: 3, name: "Camisetas", href: "#", subcateg: [
      {name: "Básica",    href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Slim Fit",  href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Oversized", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Longline",  href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
    ]},
    {id: 4, name: "Tênis", href: "#", subcateg: [
      {name: "Casual",    href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Sapatênis", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Corrida",   href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Caminhada", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "High-Top",  href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Low-Top",   href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Lifestyle", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
    ]},
    {id: 5, name: "Calças", href: "#", subcateg: [
      {name: "Skinny",   href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Slim",     href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Reta",     href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Wide leg", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Cargo",    href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
    ]},
    {id: 6, name: "Jaquetas", href: "#", subcateg: [
      {name: "Corta Vento", href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Bomber",      href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Jeans",       href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Couro",       href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
      {name: "Coach",       href: "http://localhost:4200/products-list", src: "assets/images/categories/categ-camisas.png"},
    ]}
  ];

  cards = [
    { extclass: "container-subcategory",
      miniCard: [
        {src: "assets/images/categories/categ-acessorios.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=1"
        },
        {src: "assets/images/categories/categ-camisas.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=2"
        },
        {src: "assets/images/categories/categ-camisetas.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=3"
        },
        {src: "assets/images/categories/categ-tenis.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=4"
        },
        {src: "assets/images/categories/categ-calcas.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=5"
        },
        {src: "assets/images/categories/categ-moletons.png",
         extclass: "subcategory-class",
         href: "/products-list?category=1&subcategory=6"
        }
      ]
     },
    { title: "Em ofertas!",
      extclass: "m10",
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
     { title: "Em Detaque",
       extclass: "m10",
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
        },
        {src: "assets/images/produto-teste.png",
         title: "Fone de Ouvido Headset",
         price: "1.500,25",
         currency: "R$",
         extclass: "product-class",
         href: "/product?category=1&subcategory=3"
        }
      ]
     }
    ];


  miniCard = [
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
         fullinfo: true
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
