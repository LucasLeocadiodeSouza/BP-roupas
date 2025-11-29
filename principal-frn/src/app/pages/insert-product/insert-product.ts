import { RequestForm } from './../../service/request-form';
import { ChangeDetectorRef, Component, ElementRef, inject, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Banner } from "../../components/banner/banner";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-insert-product',
  imports: [CommonModule, Banner, FormsModule],
  templateUrl: './insert-product.html',
  styleUrl: './insert-product.css'
})
export class InsertProduct implements OnInit {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  isChanging: boolean = false;
  hasEspecificationSize!: boolean;
  selectCategory: string = "0";
  selectSubCategory: string = "0";
  optionsCategories?: any;
  optionsSubCategories?: any;
  especificationColor?: any;
  especificationSize?: any;
  imageProducts: { src: string; height: string; width: string }[] = [];

  @ViewChild('selcolor',  { read: ViewContainerRef }) selcolor!: ViewContainerRef;
  @ViewChild('fileinput') fileInput!: ElementRef<HTMLInputElement>;

  selectCategories(category_id: any) {
    this.selectCategory = category_id;

    this.updateSubCategories(category_id);
    this.updateEspecificationSize(category_id);
  }

  selectSubCategories(event: any) {
    const element          = event.target;
    this.selectSubCategory = element.value;
  }

  selectEspecification(event: any) {
    const element = event.target.closest('.control-button');

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
  }

  updateCategories(){
    this.request.executeRequestGET('api/findAllCategoriesActive').subscribe({
      next: (response) => {
        this.optionsCategories = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateSubCategories(category: any){
    this.request.executeRequestGET('api/findAllSubcategoriesByCategory', {categ_id: category}).subscribe({
      next: (response) => {
        this.optionsSubCategories = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateEspecificationSize(category: any){
    this.request.executeRequestGET('api/getEspecificationSize', {categ_id: category}).subscribe({
      next: (response) => {
        this.especificationSize = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateEspecificationColor(){
    this.request.executeRequestGET('api/getEspecificationColor').subscribe({
      next: (response) => {
        this.especificationColor = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  registerImageProduct(src: any){
    const formData = new FormData();

    formData.append("image", src);
    formData.append("product_id", src);

    this.request.executeRequestPOST('api/registerImage', formData).subscribe({
      next: (response) => {
        // this.imageProducts = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  registerTempImageProduct(src: any){
    const formData = new FormData();

    formData.append("image", src);

    this.request.executeRequestPOST('api/registerTempImage', formData).subscribe({
      next: (response) => {
        this.imageProducts.push({
          src: "http://localhost:8080/api/temp/" + response.filename,
          height: "600px",
          width: "495px"
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  adapterRegisterTempImageProduct() {
    this.fileInput.nativeElement.click();
  }

  onFileSelected(event: any) {
    const selectedImage = event.target.files[0];

    if (selectedImage) {
      this.registerTempImageProduct(selectedImage);
      event.target.value = '';
    }
  }

  ngOnInit() {
    this.updateCategories();
    this.updateEspecificationColor();
  }
}
