import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../../components/order-item/order-item';

@Component({
  selector: 'app-order-checkout',
  imports: [OrderItem, CommonModule],
  templateUrl: './order-checkout.html',
  styleUrl: './order-checkout.css'
})
export class OrderCheckout {

  orderproducts = [
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    },
    {img: "assets/images/produto-teste.png",
     unid: 1,
     currency: "R$",
     price: "583.00",
     title: "Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para Capas de pega de freio manual em plástico ABS esportivo - Acabamento suave, acessórios interiores duráveis para"
    }
  ]
}
