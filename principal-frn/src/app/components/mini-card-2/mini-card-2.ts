import { ChangeDetectorRef, Component, inject, input, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartForm } from '../../service/cart-form';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-mini-card-2',
  imports: [CommonModule],
  templateUrl: './mini-card-2.html',
  styleUrl: './mini-card-2.css'
})
export class MiniCard2 {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef, private cartForm: CartForm) {}

  @Input() srcImages: any;
  @Input() prodId: any;
  @Input() title: any;
  @Input() price: any;
  @Input() currency: any;
  @Input() extclass: any;
  @Input() href: any;
  @Input() comments: any;

  addedInFavoList: boolean = false;

  createListProduct(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) {
        window.open('/insert/login', '_self');
        return;
      }

      this.request.executeRequestPOST('account/adapterCreateUserList', null, {prodId: this.prodId}).subscribe({
        next: (response) => {
          this.addedInFavoList = true;

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    });
  }
}
