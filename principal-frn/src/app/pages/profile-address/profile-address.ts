import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MiniCard } from "../../components/mini-card/mini-card";
import { AddressOption } from "../../components/address-option/address-option";
import { NewAddress } from "../../components/new-address/new-address";

@Component({
  selector: 'app-profile-address',
  imports: [MiniCard, CommonModule, AddressOption, NewAddress],
  templateUrl: './profile-address.html',
  styleUrl: './profile-address.css'
})
export class ProfileAddress {
  addresses = [
    {
      name: "Lucas Leocadio de Souza",
      address1: "Rua Joana D'arc 16",
      address2: "Jardim Liberdade, 87047-080 Maringá, PR, Brasil",
      number: "44997067494",
      active: true
    },
    {
      name: "Pedro Paulo e Alex",
      address1: "Rua Jardim Oliva 23",
      address2: "Teixera Mendes, 00000-080 Maringá, PR, Brasil",
      number: "44997786076",
      active: false
    },
  ];
}
