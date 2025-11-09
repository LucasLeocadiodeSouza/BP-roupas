import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header-categories',
  imports: [CommonModule],
  templateUrl: './header-categories.html',
  styleUrl: './header-categories.css'
})
export class HeaderCategories {
  @Input() categories?: any[] = [];

  listSubCategories?: any[] = [];
  featuredSubcategory?: string;

  updateFeaturedSubcategory(name: string){
    this.featuredSubcategory = name;
  }

  pushListSubCategories(subcategories: any[] = []){
    this.listSubCategories = [];

    for (let i = 0; i < subcategories.length; i++) {

      this.listSubCategories?.push({
        idCateg: subcategories[i].id,
        name: subcategories[i].name,
        href: subcategories[i].href,
        src: subcategories[i].src,
      });
    }
  }
}
