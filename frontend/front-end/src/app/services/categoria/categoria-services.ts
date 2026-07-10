import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategoriaResponse } from '../../models/categoria/categoria-response';

@Injectable({
  providedIn: 'root',
})
export class CategoriaServices {
  private url="http://localhost:8090/api/categoria";

  constructor(private http:HttpClient){}

  listar():Observable<CategoriaResponse[]>{
    return this.http.get<CategoriaResponse[]>(this.url);
  }
}
