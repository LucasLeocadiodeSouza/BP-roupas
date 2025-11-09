import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Home } from './pages/home/home';
import { Footer } from './components/footer/footer';
import { Header } from "./components/header/header";
import { ShoppingList } from "./components/shopping-list/shopping-list";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Home, Footer, Header, ShoppingList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('principal-frn');
}
