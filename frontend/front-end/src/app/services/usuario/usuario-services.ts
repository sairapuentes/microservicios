import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioRequest } from '../../models/login/usuario-request';
import { UsuarioResponse } from '../../models/login/usuario-response';

@Injectable({
  providedIn: 'root',
})
export class UsuarioServices {
  private url="http://localhost:8090/api/usuario";

  constructor(private http:HttpClient){}

  listar():Observable<UsuarioResponse[]>{
          return this.http.get<UsuarioResponse[]>(this.url);
      }
  
      buscarPorId(id:number):Observable<UsuarioResponse>{
          return this.http.get<UsuarioResponse>(`${this.url}/${id}`);
      }
  
      crear(producto:UsuarioRequest):Observable<UsuarioResponse>{
          return this.http.post<UsuarioResponse>(`${this.url}/crear`,producto);
      }
  
      actualizar(id:number,producto:UsuarioRequest):Observable<UsuarioResponse>{
          return this.http.put<UsuarioResponse>(`${this.url}/${id}`,producto);
      }
  
      eliminar(id:number):Observable<string>{
          return this.http.delete(`${this.url}/${id}`,{
              responseType:'text'
          });
      }
}
