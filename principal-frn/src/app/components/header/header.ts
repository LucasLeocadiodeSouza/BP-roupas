import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderCategories } from "../header-categories/header-categories";

@Component({
  selector: 'app-header',
  imports: [HeaderCategories],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  isCategoriesVisible = false;
  
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
  ]

  showOverlay() { this.isCategoriesVisible = true; }
  hideOverlay() { this.isCategoriesVisible = false; }
}
