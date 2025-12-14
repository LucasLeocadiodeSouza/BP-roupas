import { ChangeDetectorRef, Component, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';


@Component({
  selector: 'app-create-user',
  imports: [CommonModule],
  templateUrl: './create-user.html',
  styleUrl: './create-user.css'
})
export class CreateUser {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  @ViewChild('inputddd', { read: ViewContainerRef }) inputddd!: ViewContainerRef;

  textUsernameError: boolean = false;
  textEmailError:    boolean = false;
  textNumberError:   boolean = false;
  textPasswordError: boolean = false;

  textEmail:       string = "";
  textUsername:    string = "";
  textPhoneNumber: number = 0;
  textPassword:    string = "";

  isFullPhoneNumber: boolean = false

  changeUsername(event: any) {
    this.textUsername = event.target.value;
  }

  changeEmail(event: any) {
    this.textEmail = event.target.value;
  }

  changePhoneNumber(event: any) {
    const element = event.target;

    element.value = element.value.replace('-', '');

    if(!Number(element.value) || element.value.length > 11){
       element.value = element.value.slice(0, -1);
       return;
    }

    if(this.inputddd.element.nativeElement.value != "" &&
       element.value.length < 11){
        element.value = String(this.textPhoneNumber).slice(0, -1);
    }

    this.textPhoneNumber = Number(element.value);

    if(element.value.length > 10) {
      this.isFullPhoneNumber = true;
      this.inputddd.element.nativeElement.value = "(" + element.value.slice(0, 2) + ")";

      element.value = element.value.slice(2, 7) + "-" + element.value.slice(7);
    }
    else {
      this.inputddd.element.nativeElement.value = "";
      this.isFullPhoneNumber = false;
    }
  }

  changePassword(event: any) {
    this.textPassword = event.target.value;
  }
}
