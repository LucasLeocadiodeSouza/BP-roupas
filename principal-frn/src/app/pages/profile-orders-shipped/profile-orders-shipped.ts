import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from "../../components/order-item/order-item";

@Component({
  selector: 'app-profile-orders-shipped',
  imports: [CommonModule, OrderItem],
  templateUrl: './profile-orders-shipped.html',
  styleUrl: './profile-orders-shipped.css'
})
export class ProfileOrdersShipped {
  orderproducts = [
    {img: "assets/images/produto-teste.png",
     currency: "R$",
     price: "583.00",
     update: false,
     href: "/product?category=1&subcategory=3",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
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
