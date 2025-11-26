import { RequestForm } from './../../service/request-form';
import { Component, inject, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Banner } from "../../components/banner/banner";

@Component({
  selector: 'app-insert-product',
  imports: [CommonModule, Banner],
  templateUrl: './insert-product.html',
  styleUrl: './insert-product.css'
})
export class InsertProduct implements OnInit {
  private request = inject(RequestForm);

  hasEspecificationSize!: boolean;
  selectCategory: string = "0";
  selectSubCategory: string = "0";
  optionsCategories?: any;
  optionsSubCategories?: any;
  especificationColor?: any;
  especificationSize?: any;

  @ViewChild('selcolor', { read: ViewContainerRef }) selcolor!: ViewContainerRef;


  productsbanner = [
    {src: "assets/images/produto-teste.png",
     height: "577px",
    },
    {src: "assets/images/produto-teste.png",
     height: "577px"
    },
    {src: "assets/images/produto-teste.png",
     height: "577px"
    },
    {src: "assets/images/produto-teste.png",
     height: "577px"
    },
    {src: "assets/images/produto-teste.png",
     height: "577px"
    },
    {src: "assets/images/produto-teste.png",
     height: "577px"
    },
  ];

  selectCategories(event: any) {
    const element       = event.target;
    this.selectCategory = element.value;

    this.updateSubCategories(element.value);
    this.updateEspecificationSize(element.value);
  }

  selectSubCategories(event: any) {
    const element          = event.target;
    this.selectSubCategory = element.value;
  }

  selectEspecification(event: any) {
    const element = event.target.closest('.control-button');

    //const selects = element.parentNode.querySelectorAll('.selected');

    if(element.classList.contains("selected")) element.classList.remove("selected");
    else element.classList.add("selected");
  }

  updateCategories(){
    this.request.executeRequestGET('gen/findAllCategoriesActive').subscribe({
      next: (response) => {
        this.optionsCategories = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateSubCategories(category: any){
    this.request.executeRequestGET('gen/findAllSubcategoriesByCategory', {categ_id: category}).subscribe({
      next: (response) => {
        this.optionsSubCategories = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateEspecificationSize(category: any){
    this.request.executeRequestGET('gen/getEspecificationSize', {categ_id: category}).subscribe({
      next: (response) => {
        this.especificationSize = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateEspecificationColor(){
    this.request.executeRequestGET('gen/getEspecificationColor').subscribe({
      next: (response) => {
        this.especificationColor = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }


  ngOnInit() {
    this.updateCategories();
    this.updateEspecificationColor();
  }
}
