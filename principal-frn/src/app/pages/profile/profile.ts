import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { RequestForm } from '../../service/request-form';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {

  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  username: string    = "";
  email: string       = "";
  numberphone: string = "";


  getUserInformation(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) {
        window.open('/insert/login', '_self');
        return;
      }

      this.request.executeRequestPOST('account/getUserInformation', {}).subscribe({
        next: (response) => {
          var info: {username:  string,
                     email:     string,
                     telephone: string}

          info = response;

          this.username    = info.username;
          this.email       = info.email;
          this.numberphone = info.telephone;


          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    });
  }

  ngOnInit(){
    this.getUserInformation();
  }
}
