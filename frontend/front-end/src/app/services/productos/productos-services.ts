import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductosRequest } from '../../models/productos/productos-request';
import { ProductosResponse } from '../../models/productos/productos-response';

@Injectable({
  providedIn: 'root',
})
export class ProductosServices {
  private url="http://localhost:8090/api/productos";

    constructor(private http:HttpClient){}

    listar():Observable<ProductosResponse[]>{
        return this.http.get<ProductosResponse[]>(this.url);
    }

    buscarPorId(id:number):Observable<ProductosResponse>{
        return this.http.get<ProductosResponse>(`${this.url}/${id}`);
    }

    crear(producto:ProductosRequest):Observable<ProductosResponse>{
        return this.http.post<ProductosResponse>(`${this.url}/crear`,producto);
    }

    actualizar(id:number,producto:ProductosRequest):Observable<ProductosResponse>{
        return this.http.put<ProductosResponse>(`${this.url}/${id}`,producto);
    }

    eliminar(id:number):Observable<string>{
        return this.http.delete(`${this.url}/${id}`,{
            responseType:'text'
        });
    }
}
