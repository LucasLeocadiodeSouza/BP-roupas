import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../../components/order-item/order-item';
import { CartForm } from '../../service/cart-form';
import { Observable, map } from 'rxjs';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-order-checkout',
  imports: [OrderItem, CommonModule],
  templateUrl: './order-checkout.html',
  styleUrl: './order-checkout.css'
})
export class OrderCheckout {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private cartForm: CartForm) {}

  cartItems$!: Observable<any[]>;

  isEmptyCart$!: Observable<boolean>;

  purchaseImage: string[] = [];
  purchaseCompleted: boolean = false;
  initAnimation: boolean = false;
  address: {street: string, neighborhood: string, number: string, cep: string, city: string, state: string } = {street: "", neighborhood: "", number: "", cep: "", city: "", state: ""};
  fretePrice: number = 0;

  getPriceSum(){
    return this.cartForm.ItemsSum();
  }

  getPriceDelivery(){
    this.request.executeRequestGET('account/getFinalFrete').subscribe({
      next: (response) => {
        this.fretePrice = response;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getPriceSumPlusDelivery(){
    const value = Number(this.cartForm.ItemsSum()) + this.fretePrice;

    return value.toFixed(2);
  }

  registerUserCartPurchases(){
    if(this.initAnimation) return;

    this.initAnimation = true;

    this.addPurchaseImage();

    setTimeout(() => {
      this.request.executeRequestPOST('account/registerUserCartPurchases', null).subscribe({
        next: (response) => {
          this.purchaseCompleted = true;

          this.cartForm.clearCart();

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    }, 2000);
  }

  addPurchaseImage(){
    this.request.executeRequestGET('account/getAllImagesproductInCart').subscribe({
      next: (response) => {
        if(response == null) return;

        this.purchaseImage = response;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  getUserAddress(){
    this.request.executeRequestPOST('account/getActiveAddress', null).subscribe({
      next: (response: {street:       string,
                        neighborhood: string,
                        number:       string,
                        cep:          string,
                        city:         string,
                        state:        string
                      }) => {

        this.address = {
          street:       response.street,
          neighborhood: response.neighborhood,
          number:       response.number,
          cep:          response.cep,
          city:         response.city,
          state:        response.state
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(): void {
    this.cartItems$   = this.cartForm.cartItems$;
    this.isEmptyCart$ = this.cartItems$.pipe( map(cart => cart.length === 0) );
    this.cartForm.loadCart();
    this.getUserAddress();
    this.getPriceDelivery();
  }
}
