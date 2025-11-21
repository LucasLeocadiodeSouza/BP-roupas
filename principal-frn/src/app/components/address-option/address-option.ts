import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-address-option',
  imports: [CommonModule],
  templateUrl: './address-option.html',
  styleUrl: './address-option.css'
})
export class AddressOption {
  @Input() addresses: any;

  //name
  //address1
  //address2
  //number
  //active
}
