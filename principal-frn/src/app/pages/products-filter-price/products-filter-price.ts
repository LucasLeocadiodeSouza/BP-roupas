import { ChangeDetectorRef, Component, ElementRef, inject, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriesList } from "../../components/categories-list/categories-list";
import { ProductMenu } from "../../components/product-menu/product-menu";
import { RequestForm } from './../../service/request-form';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products-filter-price',
  imports: [ProductMenu, CategoriesList],
  templateUrl: './products-filter-price.html',
  styleUrl: './products-filter-price.css'
})
export class ProductsFilterPrice {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: Router, private activatedRoute: ActivatedRoute) {}

  barContainer:   any;
  titlemenu:      string = "Roupas Até R$49,90";
  category_id:    number = 0;
  subcategory_id: number = 0;


  cards: {title?: string,
          extclass: string,
          src?: string,
          miniCard: {src: string, title?: string, price?: string, currency?: string, extclass: string, href: string }[]
        }[] = [];

  loadActiveCategories(){
    this.request.executeRequestGET('api/findAllCategoriesActive').subscribe({
      next: (response) => {
        var categories: { id:     number;
                          name:   string;
                          active: boolean}[] = [];

        categories = response;

        const categformat = categories.map(category => ({
            ...category,
            href:    `/products-list?category_id=${category.id}`
        }));

        this.barContainer = [
          {
            title: "Categorias",
            buttons: categformat
          }
        ];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.loadActiveCategories();
  }
}
