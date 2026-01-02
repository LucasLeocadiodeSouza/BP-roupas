import { ChangeDetectorRef, Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-address-option',
  imports: [CommonModule],
  templateUrl: './address-option.html',
  styleUrl: './address-option.css'
})
export class AddressOption {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  @Input() addresses: any;

  setActiveAddress(sequence: number){
    this.request.executeRequestPOST('account/setActiveAddress', null, {sequence: sequence}).subscribe({
      next: (response) => { this.cdRef.detectChanges(); },
      error: (error) => { console.error('Erro:', error) }
    });
  }
}
