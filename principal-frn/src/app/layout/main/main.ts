import { Component } from '@angular/core';
import { ShoppingList } from "../../components/shopping-list/shopping-list";
import { Footer } from "../../components/footer/footer";
import { RouterOutlet } from "@angular/router";
import { Header } from "../../components/header/header";

@Component({
  selector: 'app-main',
  imports: [ShoppingList, Footer, RouterOutlet, Header],
  templateUrl: './main.html',
  styleUrl: './main.css'
})
export class Main {

}
