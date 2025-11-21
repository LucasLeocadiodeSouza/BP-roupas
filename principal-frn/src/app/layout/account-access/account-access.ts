import { Component } from '@angular/core';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { Footer } from "../../components/footer/footer";
import { RouterOutlet } from "@angular/router";
import { Header } from "../../components/header/header";

@Component({
  selector: 'app-account-access',
  imports: [CategoriesList, ProductMenu, Footer, RouterOutlet, Header],
  templateUrl: './account-access.html',
  styleUrl: './account-access.css'
})
export class AccountAccess {
  barContainer = [
    {
      title: "Seus Pedidos",
      buttons: [
                {id: 1, name: "Pedidos",   href: "/account/orders"},
                {id: 2, name: "Enviados",  href: "/account/orders-shipped"},
                {id: 3, name: "Entregues", href: "/account/orders-delivered"}
              ]
    },
    {
      buttons: [
                {id: 1, name: "Seu Perfil",  href: "/account"},
                {id: 2, name: "Sua Lista",   href: "/account/your-list"},
                {id: 2, name: "Histórico",   href: "/account/history"},
                {id: 3, name: "Endereços",   href: "/account/address"}
              ]
    }
  ];


}
