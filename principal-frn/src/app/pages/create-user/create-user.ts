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

  hasTextUsernameError: boolean = false;
  hasTextEmailError:    boolean = false;
  hasTextNumberError:   boolean = false;
  hasTextPasswordError: boolean = false;
  hasTextInternalError: boolean = false;

  textUsername:      string = "";
  textEmail:         string = "";
  textPhoneNumber:   number = 0;
  textPassword:      string = "";
  textInternalError: string = "";

  isFullPhoneNumber: boolean = false


  validFields(){
    if(this.textUsername.length < 7) this.hasTextUsernameError = true;

    if(this.textEmail == null || this.textEmail == "") this.hasTextEmailError = true;
    //if(!this.textEmail.contains("@") || !email.textEmail(".com")) this.textEmailError = true;

    if(!this.textPhoneNumber || this.textPhoneNumber == 0) this.hasTextNumberError = true;
    if(String(this.textPhoneNumber).length != 11) this.hasTextNumberError = true;

    if(this.textPassword.length < 11) this.hasTextPasswordError = true;
  }

  registerUserAccount(){
    this.validFields();

    const param = {
      username:  this.textUsername,
      email:     this.textEmail,
      telephone: this.textPhoneNumber,
      password:  this.textPassword
    }

    this.request.executeRequestPOST('account/registerUserAccount', param).subscribe({
      next: (response) => {

        window.open('/insert/login', '_self');

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);

        this.textInternalError = error.error;
        this.hasTextInternalError = true;

        // reset ts errors
        this.hasTextUsernameError = false;
        this.hasTextEmailError    = false;
        this.hasTextNumberError   = false;
        this.hasTextPasswordError = false;
        this.cdRef.detectChanges();
      }
    });
  }


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
