import { ChangeDetectorRef, Component, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-login',
  imports: [CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  textError:       boolean = false;
  textUsername:    string = "";
  textPassword:    string = "";

  changeUsername(event: any) {
    this.textUsername = event.target.value;
  }

  changePassword(event: any) {
    this.textPassword = event.target.value;
  }
}
