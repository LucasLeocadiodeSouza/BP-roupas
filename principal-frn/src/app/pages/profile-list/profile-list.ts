import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemList } from "../item-list/item-list";
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-profile-list',
  imports: [CommonModule, ItemList],
  templateUrl: './profile-list.html',
  styleUrl: './profile-list.css'
})
export class ProfileList {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  itens: {title:   string,
          href:    string,
          images:  {view: string}[],
          seqList: number
        }[] = [];

  loadAllListByUser(){
    this.request.executeRequestGET(`account/getAllUserListByUser`).subscribe({
      next: (response) => {
        var cards: {
                    useraccount_id: number,
                    nameList:       string,
                    seqList:        number,
                   }[] = [];

        cards = response;

        const newCards = cards.map(card => ({
            title:    card.nameList,
            href:     `/account/your-list/list?seqlist=${card.seqList}&title=${card.nameList}`,
            images:   [],
            seqList: card.seqList
        }));

        this.itens = [...this.itens, ...newCards];

        this.itens.forEach((item: any) => {
          this.setImagesLists(item.seqList);
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  setImagesLists(sequence: number){
    this.request.executeRequestGET(`account/findSomeProductByList`, {seqlist: sequence}).subscribe({
      next: (response) => {
        var cards: {
                    useraccount_id: number,
                    product_id:     number,
                    name:           string,
                    price:          number,
                    image:          string,
                    avarage_rating: number,
                    nameList:       string,
                    seqList:        number,
                   }[] = [];

        cards = response;

        const images = cards.map(card => ({
          view: "http://localhost:8080/api/product/" + card.image
        }));

        this.itens.forEach((item: any) => {
          if(item.seqList == sequence) item.images = images;
        });

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('Erro:', error);
      }
    });
  }

  ngOnInit(){
    this.loadAllListByUser();
  }
}
