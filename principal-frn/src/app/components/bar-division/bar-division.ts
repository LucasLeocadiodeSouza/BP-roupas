import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bar-division',
  imports: [CommonModule],
  templateUrl: './bar-division.html',
  styleUrl: './bar-division.css'
})
export class BarDivision {
  @Input() title?: any[] = [];
  @Input() buttons?: any;
  @Input() separete: boolean = true;

  listSubCategories?: any[] = [];
  featuredSubcategory?: string;

  updateFeaturedSubcategory(name: string){
    this.featuredSubcategory = name;
  }
}
