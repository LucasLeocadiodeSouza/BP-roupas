import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BarDivision } from "../bar-division/bar-division";

@Component({
  selector: 'app-categories-list',
  imports: [CommonModule, BarDivision],
  templateUrl: './categories-list.html',
  styleUrl: './categories-list.css'
})
export class CategoriesList {
  @Input() divisions?: any[] = [];
}
