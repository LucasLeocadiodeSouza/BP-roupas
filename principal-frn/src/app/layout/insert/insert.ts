import { Component } from '@angular/core';
import { Header } from "../../components/header/header";
import { RouterOutlet } from "@angular/router";
import { Footer } from "../../components/footer/footer";

@Component({
  selector: 'app-insert',
  imports: [Header, RouterOutlet, Footer],
  templateUrl: './insert.html',
  styleUrl: './insert.css'
})
export class Insert {

}
