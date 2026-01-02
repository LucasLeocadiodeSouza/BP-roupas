import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { RequestForm } from '../../service/request-form';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-new-address',
  imports: [CommonModule, FormsModule],
  templateUrl: './new-address.html',
  styleUrl: './new-address.css'
})
export class NewAddress {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  street:       string = "";
  number:       string = "";
  neighborhood: string = "";
  cep:          string = "";
  city:         string = "";
  state:        string = "";
  country:      string = "";

  changeCEPNumber(event: any) {
    const element = event.target;

    element.value = element.value.replace('-', '');

    if(element.value.length > 8){
       element.value = element.value.slice(0, -1);
       if(element.value.length > 8) element.value = element.value.slice(0, 5) + "-" + element.value.slice(5)
       return;
    }

    this.cep = element.value;

    if(element.value.length == 8) element.value = element.value.slice(0, 5) + "-" + element.value.slice(5);
  }

  registerAddress(){
    const body = {
      street:       this.street,
      number:       this.number,
      neighborhood: this.neighborhood,
      cep:          this.cep.replace('-', ''),
      city:         this.city,
      state:        this.state,
      country:      this.country
    }

    this.request.executeRequestPOST('account/registerUserAddress', body).subscribe({
      next: (response) => { window.open("/account/address", "_self") },
      error: (error) => { console.error('Erro:', error); }
    });
  }
}
