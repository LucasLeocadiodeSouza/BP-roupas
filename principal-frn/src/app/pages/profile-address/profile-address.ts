import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddressOption } from "../../components/address-option/address-option";
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-profile-address',
  imports: [CommonModule, AddressOption],
  templateUrl: './profile-address.html',
  styleUrl: './profile-address.css'
})
export class ProfileAddress {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  addresses!: {
    address1: string;
    address2: string;
    country: string;
    active: boolean;
  }[];


  getUserAddress(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) {
        window.open('/insert/login', '_self');
        return;
      }

      this.request.executeRequestPOST('account/getUserAddress', {}).subscribe({
        next: (response) => {
          var info: {
            street:       string,
            neighborhood: string,
            number:       string,
            cep:          string,
            city:         string,
            state:        string,
            active:       boolean
          }[];

          info = response;

          if(info == null) return;

          const addressFormat = info.map((address: any) => ({
            address1: address.street + " " + address.number,
            address2: address.neighborhood + ", " + address.cep  + " " + address.city + ", " + address.state,
            country:  address.country,
            active:   address.active
          }));

          this.addresses = addressFormat;

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    });
  }

  ngOnInit(){
    this.getUserAddress();
  }
}
