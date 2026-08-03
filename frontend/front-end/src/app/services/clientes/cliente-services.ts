import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientesRequest } from '../../models/clientes/clientes-request';
import { ClientesResponse } from '../../models/clientes/clientes-response';

@Injectable({
  providedIn: 'root',
})
export class ClienteServices {
  private url="http://localhost:8090/api/clientes";

  constructor(private http:HttpClient){}

  listar():Observable<ClientesResponse[]>{
    return this.http.get<ClientesResponse[]>(this.url);
  }

  buscarPorId(id:number):Observable<ClientesResponse>{
    return this.http.get<ClientesResponse>(`${this.url}/${id}`);
  }

  crear(clientes:ClientesRequest):Observable<ClientesResponse>{
    return this.http.post<ClientesResponse>(`${this.url}/crear`,clientes);
  }

  actualizar(id:number,clientes:ClientesRequest):Observable<ClientesResponse>{
    return this.http.put<ClientesResponse>(`${this.url}/${id}`,clientes);
  }

  eliminar(id:number):Observable<string>{
    return this.http.delete(`${this.url}/${id}`,{
      responseType:'text'
    });
  }
}
