import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestForm {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  // getProdutos(): Observable<any[]> {
  //   return this.http.get<any[]>(`${this.apiUrl}/produtos`);
  // }

  // // POST - Criar dados
  // criarProduto(produto: any): Observable<any> {
  //   return this.http.post(`${this.apiUrl}/produtos`, produto);
  // }

  executeRequestGET(path: string, params?: any): Observable<any> {
      let url = `${this.apiUrl}/${path}`;

      // Adiciona query parameters se existirem
      if (params) {
          const queryParams = new URLSearchParams(params).toString();
          url += '?' + queryParams;
      }

      return this.http.get(url);
  }

  executeRequestPOST(path: string, body: any, params?: any): Observable<any> {
    let url = `${this.apiUrl}/${path}`;

    // Adiciona query parameters se existirem
    if (params) {
        const queryParams = new URLSearchParams(params).toString();
        url += '?' + queryParams;
    }

    return this.http.post(url, body);
  }
}
