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

  loginUserAccount(){
    const param = {
      username: this.textUsername,
      password: this.textPassword
    }

    this.request.executeRequestPOST('auth/login', param).subscribe({
      next: (response) => {
        window.open('/', '_self');
      },
      error: (error) => {
        console.error('Erro:', error);

        this.textError = true;
        this.cdRef.detectChanges();
      }
    });

  }



  changeUsername(event: any) {
    this.textUsername = event.target.value;
    this.textError    = false;
  }

  changePassword(event: any) {
    this.textPassword = event.target.value;
    this.textError    = false;
  }
}
