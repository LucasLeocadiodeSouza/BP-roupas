import { ChangeDetectorRef, Component, HostBinding, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MiniCard } from "../../components/mini-card/mini-card";
import { ActivatedRoute } from '@angular/router';
import { RequestForm } from '../../service/request-form';
import { Comments } from "../../components/comments/comments";
import { StarRating } from "../../components/star-rating/star-rating";
import { CartForm } from '../../service/cart-form';
import { Banner } from "../../components/banner-fit/banner-fit";


@Component({
  selector: 'app-product',
  imports: [CommonModule, MiniCard, Comments, StarRating, Banner],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private route: ActivatedRoute, private cartForm: CartForm) {}

  @ViewChild('buttcolor', { read: ViewContainerRef }) buttcolor!: ViewContainerRef;
  @ViewChild('divcomments', { read: ViewContainerRef }) divcomments!: ViewContainerRef;

  category:    {id: number, name: string} = {id: 0, name: ""};
  subcategory: {id: number, name: string} = {id: 0, name: ""};
  product:     {id: number, name: string, description: string, price: number, discount: number, active: boolean, score: number, total_comments: number; storage: number} = {id: 0, name: "", description: "", price: 0, discount: 0, active: true, score: 0, total_comments: 0, storage: 0};
  quantity:    number = 1;

  textColorErro: boolean = false;
  textSizeErro:  boolean = false;

  currency: string    = "";
  sales:    string    = "";

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
                     href:     string;
                     score:    number;
                    }[] = [];

  banners: { src: string; height: string; width: string }[] = [];

  openMoreOptions: boolean = false;
  openContainerList: boolean = false;
  openContainerCreateList: boolean = false;
  addedInTheList: boolean = false;

  listName: string = "";

  itens: {title:   string,
          images:  {view: string}[],
          seqList: number
        }[] = [];


  // Quantity effect
  paddingValue:        number = 17;
  widthGlitch:         number = 0;
  speedGlitch:         number = 0;
  speedGlitchHover:    number = 0;
  backgroundAddButton: string = "var(--color-primary, #292617)";

  @HostBinding('style.--speed-glitch-hover')
  get speedGlitchHoverCss(): string {
    return `${this.speedGlitchHover}s`;
  }

  @HostBinding('style.--glitch-width')
  get windthGlitchCss(): string {
    return `${this.widthGlitch}px`;
  }

  @HostBinding('style.--speed-glitch')
  get speedGlitchCss(): string {
    return `${this.speedGlitch}s`;
  }

  @HostBinding('style.--background-add-button')
  get glitchBackgroundCss(): string {
    return `${this.backgroundAddButton}`;
  }

  @HostBinding('style.--padding-add-button')
  get paddingCss(): string {
    return `${this.paddingValue}px`;
  }

  changeOpenMoreOptions(){
    this.openMoreOptions = !this.openMoreOptions;
  }

  oneMoreQuantity(plus: boolean) {
    if (plus) {
      if (this.quantity + 1 > this.product.storage) return;
      this.quantity++;
    } else {
      if (this.quantity <= 1) return;
      this.quantity--;
    }

    const MIN_PADDING = 17; // px
    const MAX_PADDING = 22; // px

    const progress = this.quantity / this.product.storage;
    const easedProgress = Math.pow(progress, 1.8);

    this.setEffectAddButton(progress);

    this.paddingValue =
      MIN_PADDING + (MAX_PADDING - MIN_PADDING) * easedProgress;
  }

  setEffectAddButton(progress: number){
    if(progress == 1) {
      this.backgroundAddButton = "#d10000";
      this.speedGlitch         = 1.4;
      this.widthGlitch         = 2;
      this.speedGlitchHover    = 1;
    }else if(progress > 0.9) {
      this.backgroundAddButton = "#a50000";
      this.speedGlitch         = 1.3;
      this.widthGlitch         = 2;
      this.speedGlitchHover    = 1;
    }else if(progress > 0.7) {
      this.backgroundAddButton = "#6f0000";
      this.speedGlitch         = 2;
      this.widthGlitch         = 1;
    }else {
      this.backgroundAddButton = "var(--color-primary, #292617)";
      this.speedGlitch         = 0;
      this.widthGlitch         = 0;
      this.speedGlitchHover    = 0;
    }
  }

  getNetPrice(){
    const result = this.product.price - this.product.discount;
    return result.toFixed(2);
  }

  productHasDiscount(){
    return this.product.discount != 0 && this.product.discount != null;
  }

  selectEspecificationSize(event: any) {
    if(!this.product.active) return;

    const element = event.target.closest('.control-button');

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
    this.specificationSizeSelected  = element.id;
    this.specificationColorSelected = "";
    this.textSizeErro               = false;

    this.getSpecificationColorByProduct();
  }

  selectEspecificationColor(event: any) {
    if(!this.product.active) return;

    const element = event.target.closest('.control-button');

    if(element.classList.contains("inactive")) return;

    const selects = element.parentNode.querySelectorAll('.selected');
    selects.forEach((sel: any) => {
      sel.classList.remove("selected");
    });

    element.classList.add("selected");
    this.specificationColorSelected = element.id;

    this.textColorErro = false;
  }

  rollForTheComments(){
    this.divcomments.element.nativeElement.scrollIntoView({
      behavior: 'smooth',
      block: 'start'
    });
  }

  loadProductsInformation(){
    var param = this.route.snapshot.root.queryParams;

    this.request.executeRequestGET('api/getProductInformation', param).subscribe({
      next: (response) => {
        var prod: { product_id:      number;
                    name:            string;
                    description:     string;
                    price:           number;
                    discount:        number;
                    srcimage:        string;
                    active:          boolean;
                    category_id:     number;
                    subcategory_seq: number;
                    avarage_rating:  number;
                    total_comments:  number;
                    storage:         number;
                   }[] = [];

        prod = response;

        if(prod.length == 0) console.error('Erro:', "Nao encontrado informacao para o produto");

        this.product     = {id:             prod[0].product_id,
                            name:           prod[0].name,
                            description:    prod[0].description,
                            price:          prod[0].price,
                            discount:       prod[0].discount,
                            active:         prod[0].active,
                            score:          Math.floor(prod[0].avarage_rating),
                            total_comments: prod[0].total_comments,
                            storage:        10
                          };

        this.category.id    = prod[0].category_id;
        this.subcategory.id = prod[0].subcategory_seq

        this.currency    = "R$";
        this.sales       = "0";

        this.banners = prod.map(prod => ({
            ...this.banners,
            src: "http://localhost:8080/api/product/" + prod.srcimage,
            height: "600px",
            width: "495px"
        }));

        this.registerUserHistory();
        this.getSubcategoryName();

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getSubcategoryName(){
    this.request.executeRequestGET('api/getSubCategoryByProduct', {id: this.product.id}).subscribe({
      next: (response) => {
        if (!response) return;

        this.category    = {id: response.category_id, name: response.category_name};
        this.subcategory = {id: response.subcategory_seq, name: response.subcategory_name};

        this.getSizesSpecification();

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  loadProductsList(){
    this.request.executeRequestGET('api/getSimilarProductCard', {category_id: this.category.id, subcategory_id: this.subcategory.id}).subscribe({
      next: (response) => {
        var cards: { product_id:      string;
                     name:            string;
                     description:     string;
                     price:           string;
                     srcimage:        string;
                     category_id:     string;
                     subcategory_seq: string;
                     avarage_rating:  number;}[] = [];

        cards = response;

        const newCards = cards.map(card => ({
            src:      "http://localhost:8080/api/product/product_" + card.product_id + "_1" + card.srcimage.substring(card.srcimage.lastIndexOf(".")),
            title:    card.name,
            price:    card.price,
            fullinfo: true,
            currency: "R$",
            extclass: "line-clamp2 itens-class",
            href:     `/product?id=${card.product_id}`,
            score:    Math.floor(card.avarage_rating)
        }));

        this.productCardList = [...this.productCardList, ...newCards];

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getSizesSpecification(){
    this.request.executeRequestGET('api/getSpecificationSize', {category_id: this.category.id}).subscribe({
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

  getColorsSpecification(){
    this.request.executeRequestGET('api/getSpecificationColor').subscribe({
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

  getSpecificationColorByProduct(){
    this.request.executeRequestGET('api/getSpecificationColorByProduct', {id: this.product.id, size_id: this.specificationSizeSelected}).subscribe({
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

  registerUserHistory(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) return;

      this.request.executeRequestPOST('account/registerUserHistory', {product_id: this.product.id}).subscribe({
        next: (response) => {},
        error: (error) => console.error('Erro:', error)
      });
    });
  }

  addToCart() {
    if(Number(this.specificationSizeSelected) == 0) {
      this.textSizeErro  = true;
      return;
    }

    if(Number(this.specificationColorSelected) == 0) {
      this.textColorErro = true;
      return;
    }

    const item = { product_id: this.product.id,
                   size_id:    Number(this.specificationSizeSelected),
                   color_id:   Number(this.specificationColorSelected),
                   quantity:   this.quantity};

    this.cartForm.registerProductFromCart(item);
  }

  buyNow(){
    this.addToCart();
  }

  openContainerLists(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) {
        window.open('/insert/login', '_self');
        return;
      }

      this.openContainerList = true;
      this.addedInTheList    = false;
      this.loadAllListByUser();

      this.cdRef.detectChanges();
    });
  }

  closeContainerLists(){
    this.openContainerList = false;
  }

  openContainerCreateLists(){
    this.openContainerList       = false;
    this.openContainerCreateList = true;
  }

  closeContainerCreateList(){
    this.openContainerCreateList = false;
    this.openContainerList       = true;

    this.listName = "";
    this.loadAllListByUser();
  }

  changeListName(event: any){
    this.listName = event.target.value;
  }

  loadAllListByUser(){
    this.request.executeRequestGET(`account/getAllUserListByUser`).subscribe({
      next: (response) => {
        var cards: {
                    useraccount_id: number,
                    nameList:       string,
                    seqList:        number,
                   }[] = [];

        cards = response;

        const newCards = cards.map(card => ({
            title:    card.nameList,
            images:   [],
            seqList: card.seqList
        }));

        this.itens = newCards;

        this.itens.forEach((item: any) => {
          this.setImagesLists(item.seqList);
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  setImagesLists(sequence: number){
    this.request.executeRequestGET(`account/findSomeProductByList`, {seqlist: sequence}).subscribe({
      next: (response) => {
        var cards: {
                    useraccount_id: number,
                    product_id:     number,
                    name:           string,
                    price:          number,
                    image:          string,
                    avarage_rating: number,
                    nameList:       string,
                    seqList:        number,
                   }[] = [];

        cards = response;

        const images = cards.map(card => ({
          view: "http://localhost:8080/api/product/" + card.image
        }));

        this.itens.forEach((item: any) => {
          if(item.seqList == sequence) item.images = images;
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  createListUser(){
    this.request.executeRequestPOST('account/adapterCreateUserList', null, {listName: this.listName, prodId: this.product.id}).subscribe({
      next: (response) => {
        this.closeContainerCreateList();

        this.addedInTheList = true;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  createListProduct(sequence: number){
    this.request.executeRequestPOST('account/createUserListProd', null, {sequence: sequence, prodId: this.product.id}).subscribe({
      next: (response) => {
        this.loadAllListByUser();
        this.addedInTheList = true;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit() {
    this.loadProductsInformation();
    this.getColorsSpecification();
    this.loadProductsList();
  }
}
