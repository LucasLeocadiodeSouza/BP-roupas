import { Component } from '@angular/core';
import { OrderItem } from "../../components/order-item/order-item";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile-order',
  imports: [OrderItem, CommonModule],
  templateUrl: './profile-order.html',
  styleUrl: './profile-order.css'
})
export class ProfileOrder {
  orderproducts = [
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    }
  ]
}
