import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InventarioRequest } from '../../models/inventario/inventario-request';
import { InventarioResponse } from '../../models/inventario/inventario-response';
import { InventarioRestarRequest } from '../../models/inventario/inventario-restar-request';

@Injectable({
  providedIn: 'root',
})
export class InventarioServices {
  private url="http://localhost:8090/api/inventario"

  constructor(private http:HttpClient){}

  listar(): Observable<InventarioResponse[]>{
    return this.http.get<InventarioResponse[]>(this.url);
  }

  crear(inventario:InventarioRequest): Observable<InventarioResponse>{
    return this.http.post<InventarioResponse>(`${this.url}/crear`,inventario);
  }

  buscarPorId(id:number):Observable<InventarioResponse>{
    return this.http.get<InventarioResponse>(`${this.url}/${id}`);
  }

  buscarPorProducto(idProducto:number, idSede:number):Observable<InventarioResponse>{
    return this.http.get<InventarioResponse>(`${this.url}/producto/${idProducto}/sede/${idSede}`);
  }

  actualizar(id:number,inventario:InventarioRequest): Observable<InventarioResponse>{
    return this.http.put<InventarioResponse>(`${this.url}/${id}`,inventario);
  }

  restarStock(request:InventarioRestarRequest): Observable<string>{
    return this.http.put(`${this.url}/restarStock`, request, {
      responseType:'text'
    });
  }

  eliminar(id:number):Observable<string>{
    return this.http.delete(`${this.url}/${id}`,{
      responseType:'text'
    });
  }

}
