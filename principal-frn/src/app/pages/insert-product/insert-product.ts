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
  hasSpecificationSize: boolean = false;
  selectCategory: string = "0";
  selectSubCategory: string = "0";
  optionsCategories?: any;
  optionsSubCategories?: any;
  specificationColor?: any;
  specificationSize?: any;
  imageTempProducts: { src: string; height: string; width: string }[] = [];
  imageProducts: File[] = [];

  productId: string = '';
  productName: string = '';
  productDescription: string = '';
  productStorage: number = 0;
  productPrice: number = 0;

  @ViewChild('fileinput')              fileInput!: ElementRef<HTMLInputElement>;
  @ViewChild('divSpecificationSize')   divSpecificationSize!: ElementRef<HTMLInputElement>;
  @ViewChild('divSpecificationColors') divSpecificationColors!: ElementRef<HTMLInputElement>;

  getSpecList(div: any) {
    const element  = div.nativeElement;

    const selected = element.parentNode.querySelectorAll('.selected');

    if(!selected) {
      element.parentNode.querySelector('.alert').classList.add("alert-active");

      return ["0"];
    }

    const spec_ids: string[] = [];

    selected.forEach((spec: any) => { spec_ids.push(spec.id.split("#")[1]) });

    return spec_ids;
  }

  getSpecificationUnique(div: any) {
    const element = div.nativeElement;

    const selected = element.parentNode.querySelector('.selected');

    if(!selected) {
      element.parentNode.querySelector('.alert').classList.add("alert-active");

      return "0";
    }

    const spec_id = selected.id;

    return spec_id.split("#")[1];
  }


  selectCategories(category_id: any) {
    this.selectCategory = category_id;

    this.updateSubCategories(category_id);
    this.updateEspecificationSize(category_id);
  }

  selectSubCategories(event: any) {
    const element          = event.target.closest('select');
    this.selectSubCategory = element.value;
  }

  selectSpecificationSize(event: any) {
    const element = event.target.closest('.control-button');

    element.parentNode.parentNode.querySelector('.alert').classList.remove("alert-active");

    if(element.classList.contains("selected")){
      element.classList.remove("selected");
      return
    }

    element.classList.add("selected");
  }

  selectSpecification(event: any) {
    const element = event.target.closest('.control-button');

    element.parentNode.parentNode.querySelector('.alert').classList.remove("alert-active");

    if(element.classList.contains("selected")) {
      element.classList.remove("selected");
      return
    }

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
    this.request.executeRequestGET('api/getSpecificationSize', {categ_id: category}).subscribe({
      next: (response) => {
        this.hasSpecificationSize = response.length > 0;
        this.specificationSize    = response;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  updateSpecificationColor(){
    this.request.executeRequestGET('api/getSpecificationColor').subscribe({
      next: (response) => {
        this.specificationColor = response;
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  registerTempImageProduct(file: File){
    const formData = new FormData();

    formData.append("image", file);

    this.imageProducts.push(file);

    this.request.executeRequestPOST('api/registerTempImage', formData).subscribe({
      next: (response) => {

        this.imageTempProducts = [...this.imageTempProducts, {
            src: "http://localhost:8080/api/temp/" + response.filename,
            height: "600px",
            width: "495px"
          }
        ];

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

  registerProduct(){
    const formData = new FormData();

    for (let file of this.imageProducts) {
      formData.append("images[]", file);
    }

    const sizes = this.getSpecList(this.divSpecificationSize);

    sizes.forEach(id => {
        formData.append("size_id[]", id.toString());
    });

    this.request.executeRequestPOST('api/registerProduct', formData, {product_id:      this.productId,
                                                                      name:            this.productName,
                                                                      description:     this.productDescription,
                                                                      storage:         this.productStorage,
                                                                      price:           this.productPrice,
                                                                      category_id:     this.selectCategory,
                                                                      subcategory_seq: this.selectSubCategory,
                                                                      color_id:        this.getSpecificationUnique(this.divSpecificationColors)}).subscribe({
      next: (response) => {
        window.location.href = "/insert";
      },
      error: (error) => {
        alert(error.error);
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit() {
    this.updateCategories();
    this.updateSpecificationColor();
  }
}
