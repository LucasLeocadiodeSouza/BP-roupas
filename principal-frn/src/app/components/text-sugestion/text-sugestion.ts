import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestForm } from '../../service/request-form';

@Component({
  selector: 'app-text-sugestion',
  imports: [CommonModule],
  templateUrl: './text-sugestion.html',
  styleUrl: './text-sugestion.css'
})
export class TextSugestion {
  @Input() show: boolean = false;
  @Input() sugestions: {
    image: string,
    title: string,
    href:  string
  }[] = [];
}
