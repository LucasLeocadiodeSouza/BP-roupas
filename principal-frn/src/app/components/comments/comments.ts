import { ChangeDetectorRef, Component, inject, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';
import { StarFilter } from "../star-filter/star-filter";
import { StarRating } from "../star-rating/star-rating";

@Component({
  selector: 'app-comments',
  imports: [CommonModule, StarFilter, StarRating],
  templateUrl: './comments.html',
  styleUrl: './comments.css'
})
export class Comments {
  private request = inject(RequestForm);

  constructor(private cdRef: ChangeDetectorRef) {}

  @Input() product_id: number = 0;

  totalComments:   number = 0;
  averageRating:   number = 0;
  starFilterValue: number = 0;

  comments: { id:     number;
              name:   string;
              date:   Date;
              rating: number;
              text:   string }[] = [];

  endPage:    boolean = false;
  pageNumber: number  = -1;

  getCommentsByProductId(changeInRating: boolean = false) {
    this.pageNumber = this.pageNumber + 1;
    this.endPage    = false;

    if(changeInRating) this.pageNumber = 0;

    const requestParams = {
        id:     this.product_id,
        page:   this.pageNumber,
        rating: this.starFilterValue
    };

    this.request.executeRequestGET('api/getCommentsByProductId', requestParams).subscribe({
      next: (response) => {
        let commentsresponse: { comment_id:     number;
                                product_id:     number;
                                user_name:      string;
                                description:    string;
                                rating:         number;
                                created_at:     string;
                                average_rating: number;
                                total_comments: number;
                              }[] = [];


                              

        commentsresponse = response;

        if(changeInRating){
          this.comments = [
            ...commentsresponse.map(comment => ({
              id:     comment.comment_id,
              name:   comment.user_name,
              date:   new Date(comment.created_at),
              rating: comment.rating,
              text:   comment.description
            }))
          ];
        }else{
          this.comments = [
            ...this.comments,
            ...commentsresponse.map(comment => ({
              id:     comment.comment_id,
              name:   comment.user_name,
              date:   new Date(comment.created_at),
              rating: comment.rating,
              text:   comment.description
            }))
          ];

          this.totalComments = commentsresponse.length > 0 ? commentsresponse[0].total_comments : 0;
          this.averageRating = commentsresponse.length > 0 ? Math.floor(commentsresponse[0].average_rating) : 1;
          if(commentsresponse.length == 0) this.endPage = true;
        }

        this.cdRef.detectChanges();
      },
      error: (error) => {
        console.error('There was an error!', error);
      }
    });
  }

  likeComment(event: any) {
    const element = event.target.closest('.comment-like');
    element.classList.add("comment-like-active");
  }

  getValue(value: number) {
    this.starFilterValue = value;

    this.getCommentsByProductId(true);
  }

  backToStart() {
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }

  ngOnInit() {
    this.getCommentsByProductId();
  }
}
