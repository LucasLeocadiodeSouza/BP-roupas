import { Component } from '@angular/core';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { Footer } from "../../components/footer/footer";
import { RouterOutlet } from "@angular/router";
import { Header } from "../../components/header/header";

@Component({
  selector: 'app-account-access',
  imports: [CategoriesList, Footer, RouterOutlet, Header],
  templateUrl: './account-access.html',
  styleUrl: './account-access.css'
})
export class AccountAccess {
  barContainer = [
    {
      title: "Sua conta",
      buttons: [
                {id: 1, name: "Seu Perfil",  href: "http://localhost:4200/account"},
                {id: 2, name: "Suas Listas", href: "http://localhost:4200/account/your-list"},
                {id: 2, name: "Histórico",   href: "http://localhost:4200/account/history"},
                {id: 3, name: "Endereços",   href: "http://localhost:4200/account/address"}
              ]
    },
    {
      title: "Seus Pedidos",
      buttons: [
                {id: 1, name: "Pedidos",   href: "http://localhost:4200/account/orders"}
              ]
    }
  ];


}
