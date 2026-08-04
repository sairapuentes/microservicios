import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RolRequest } from '../../models/login/rol-request';
import { RolResponse } from '../../models/login/rol-response';

@Injectable({
  providedIn: 'root',
})
export class RolServices {
  private url = "http://localhost:8090/api/rol";

  constructor(private http:HttpClient){}
  
    listar(): Observable<RolResponse[]>{
      return this.http.get<RolResponse[]>(this.url);
    }
  
    buscarPorId(id:number):Observable<RolResponse>{
      return this.http.get<RolResponse>(`${this.url}/${id}`);
    }
  
    crear(rol:RolRequest):Observable<RolResponse>{
      return this.http.post<RolResponse>(`${this.url}/crear`, rol);
    }
  
    actualizar(id:number,rol:RolRequest):Observable<RolResponse>{
      return this.http.put<RolResponse>(`${this.url}/${id}`, rol);
    }
  
    eliminar(id:number):Observable<string>{
      return this.http.delete(`${this.url}/${id}`,{
        responseType:'text'
      });
    }
}
