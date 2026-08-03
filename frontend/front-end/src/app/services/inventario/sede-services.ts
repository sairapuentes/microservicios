import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SedeRequest } from '../../models/inventario/sede-request';
import { SedeResponse } from '../../models/inventario/sede-response';

@Injectable({
  providedIn: 'root',
})
export class SedeServices {
  private url = "http://localhost:8090/api/sede";

  constructor(private http:HttpClient){}

  listar(): Observable<SedeResponse[]>{
    return this.http.get<SedeResponse[]>(this.url);
  }

  buscarPorId(id:number):Observable<SedeResponse>{
    return this.http.get<SedeResponse>(`${this.url}/${id}`);
  }

  crear(sede:SedeRequest):Observable<SedeResponse>{
    return this.http.post<SedeResponse>(`${this.url}/crear`, sede);
  }

  actualizar(id:number,sede:SedeRequest):Observable<SedeResponse>{
    return this.http.put<SedeResponse>(`${this.url}/${id}`, sede);
  }

  eliminar(id:number):Observable<string>{
    return this.http.delete(`${this.url}/${id}`,{
      responseType:'text'
    });
  }
}
