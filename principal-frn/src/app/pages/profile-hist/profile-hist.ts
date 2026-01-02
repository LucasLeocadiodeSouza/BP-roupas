import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MiniCard } from "../../components/mini-card/mini-card";
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-profile-hist',
  imports: [MiniCard, CommonModule],
  templateUrl: './profile-hist.html',
  styleUrl: './profile-hist.css'
})
export class ProfileHist {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  itens: { src:      string;
           title:    string;
           price:    string;
           currency: string;
           fullinfo: boolean;
           extclass: string;
           href:     string;
           score:    number;
           }[] = [];

  getUserHistory(){
    this.request.isLoggedIn().subscribe(isLogged =>{
      if(!isLogged) {
        window.open('http://localhost:4200/insert/login', '_self');
        return;
      }

      this.request.executeRequestPOST('account/getAllUserHistory', {}).subscribe({
        next: (response) => {
          var info: {
            useraccount_id: number,
            product_id:     number,
            name:           string,
            price:          string,
            image:          string,
            avarage_rating: number
          }[];

          info = response;

          if(info == null) return;

          const histFormat = info.map((hist: any) => ({
            src:      "http://localhost:8080/api/product/" + hist.image,
            title:    hist.name,
            price:    hist.price,
            currency: "R$",
            extclass: "itens-class",
            fullinfo: true,
            href:     "/product?id=" + hist.product_id,
            score:    Math.floor(hist.avarage_rating)
          }));

          this.itens = histFormat;

          this.cdRef.detectChanges();
        },
        error: (error) => {
          console.error('Erro:', error);
        }
      });
    });
  }

  ngOnInit(){
    this.getUserHistory();
  }
}
