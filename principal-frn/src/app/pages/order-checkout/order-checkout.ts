import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderItem } from '../../components/order-item/order-item';
import { CartForm } from '../../service/cart-form';
import { Observable } from 'rxjs';
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

  purchaseImage: string[] = [];
  purchaseCompleted: boolean = false;

  getPriceSum(){
    return this.cartForm.ItemsSum();
  }

  getPriceDelivery(){
    return this.cartForm.ItemsSum();
  }

  getPriceSumPlusDelivery(){
    return this.cartForm.ItemsSum();
  }

  registerUserCartPurchases(){
    this.request.executeRequestPOST('account/registerUserCartPurchases', null).subscribe({
      next: (response) => {
        this.purchaseCompleted = true;

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
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

  ngOnInit(): void {
    this.cartItems$ = this.cartForm.cartItems$;
    this.cartForm.loadCart();
    this.addPurchaseImage();
  }

  ThisEmptyCart(){
    let thisEmptycart = false;

    this.cartForm.cartItems$.subscribe((cart) => { thisEmptycart = cart.length == 0; });

    return thisEmptycart;
  }
}
