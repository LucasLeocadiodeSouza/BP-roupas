import { ChangeDetectorRef, Component, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Banner } from "../../components/banner/banner";
import { MiniCard } from "../../components/mini-card/mini-card";
import { ActivatedRoute } from '@angular/router';
import { RequestForm } from '../../service/request-form';
import { spec } from 'node:test/reporters';

@Component({
  selector: 'app-product',
  imports: [Banner, CommonModule, MiniCard],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute) {}

  @ViewChild('buttcolor', { read: ViewContainerRef }) buttcolor!: ViewContainerRef;

  category: string    = "";
  subcategory: string = "";
  product: string     = "";
  description: string = "";
  price: string       = "";
  active: boolean     = true;
  currency: string    = "";
  sales: string       = "";
  score: string       = "";

  specificationSize:  { id: number; size: string}[] = [];
  specificationColor: { id: number; color: string; extclass: string}[] = [];

  specificationSizeSelected: string  = "";
  specificationColorSelected: string = "";

  productCardList: { src:      string;
                     title:    string;
                     price:    string;
                     currency: string;
                     fullinfo: boolean;
                     extclass: string;
                     href:     string }[] = [];

  selectEspecificationSize(event: any) {
    if(!this.active) return;

    const element = event.target.closest('.control-button');

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
    this.specificationSizeSelected = element.id;

    this.getSpecificationSizeByProduct();
  }

  selectEspecificationColor(event: any) {
    if(!this.active) return;

    const element = event.target.closest('.control-button');

    if(element.classList.contains("inactive")) return;

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
    this.specificationColorSelected = element.id;
  }

  banners: { src: string; height: string; width: string }[] = [];

  getTitleSearch(){
    const paramsurl = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getSubCategoryById', paramsurl).subscribe({
      next: (response) => {

        this.category = response.category_name;

        if(response.subcategory_name) this.subcategory = response.subcategory_name;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  loadProductsList(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getSimilarProductCard', param).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           string;
                     srcimage:        string;
                     category_id:     string;
                     subcategory_seq: string }[] = [];

        cards = response;

        const newCards = cards.map(card => ({
            src:      "http://localhost:8080/api/product/product_" + card.product_id + "_1" + card.srcimage.substring(card.srcimage.lastIndexOf(".")),
            title:    card.name,
            price:    card.price,
            fullinfo: true,
            currency: "R$",
            extclass: "itens-class",
            href:     `/product?id=${card.product_id}&category_id=${card.category_id}&subcategory_id=${card.subcategory_seq}`
        }));

        this.productCardList = [...this.productCardList, ...newCards];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getColorsSpecification(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getColorsSpecification', param).subscribe({
      next: (response) => {
        var colors: { id: number; color: string;}[] = [];

        colors = response;

        const colorsFormat = colors.map(color => ({
            color:     color.color,
            id:        color.id,
            extclass:  "inactive"
        }));

        this.specificationColor = [...this.specificationColor, ...colorsFormat];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getSizesSpecification(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getSizesByCategory', param).subscribe({
      next: (response) => {
        var sizes: { id: number; size: string;}[] = [];

        sizes = response;

        const sizesFormat = sizes.map(size => ({
            size:      size.size,
            id:        size.id,
            extclass:  ""
        }));

        this.specificationSize = [...this.specificationSize, ...sizesFormat];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getSpecificationSizeByProduct(){
    var param = this.route.snapshot.root.queryParams;

    const requestParams = {
        ...param,
        size_id: this.specificationSizeSelected
    };

    this.request.executeRequestGET('api/getSpecificationColorByProduct', requestParams).subscribe({
      next: (response) => {
        var colors: { prod_id:  number;
                      size_id:  number;
                      size:     string;
                      color_id: number;
                      color:    string;
                      storage:  number;
                    }[] = [];

        colors = response;

        this.specificationColor.forEach(speccolor => { speccolor.extclass = "inactive"; });

        const buttcolorElement = this.buttcolor.element.nativeElement;

        buttcolorElement.childNodes.forEach((child: any) => {
          if (child.nodeType === Node.COMMENT_NODE) return;

          child.classList.remove("selected");
        });

        this.specificationColor.forEach(speccolor => {
          colors.forEach(color => {
            if(color.color_id == speccolor.id) speccolor.extclass = "active";
          });
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  loadProductsInformation(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getProductInformation', param).subscribe({
      next: (response) => {
        var prod: { product_id:      string;
                    name:            string;
                    description:     string;
                    price:           string;
                    srcimage:        string;
                    active:          boolean;
                    category_id:     string;
                    subcategory_seq: string;
                   }[] = [];

        prod = response;

        if(prod.length == 0) console.error('Erro:', "Nao encontrado informacao para o produto");

        this.product     = prod[0].name;
        this.description = prod[0].description;
        this.price       = prod[0].price;
        this.active      = prod[0].active,
        this.currency    = "R$";
        this.sales       = "0";
        this.score       = "5.0";

        this.banners = prod.map(prod => ({
            ...this.banners,
            src: "http://localhost:8080/api/product/" + prod.srcimage,
            height: "600px",
            width: "495px"
        }));

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit() {
    this.getTitleSearch();
    this.loadProductsInformation();
    this.loadProductsList();
    this.getColorsSpecification();
    this.getSizesSpecification();
  }
}
