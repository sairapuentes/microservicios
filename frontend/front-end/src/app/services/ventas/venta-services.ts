import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VentaRequest } from '../../models/ventas/venta-request';
import { VentaResponse } from '../../models/ventas/venta-response';

@Injectable({
  providedIn: 'root',
})
export class VentaServices {
  private url="http://localhost:8090/api/ventas";

    constructor(private http:HttpClient){}

    listar():Observable<VentaResponse[]>{
        return this.http.get<VentaResponse[]>(this.url);
    }

    buscarPorId(id:number):Observable<VentaResponse>{
        return this.http.get<VentaResponse>(`${this.url}/${id}`);
    }

    crear(ventas:VentaRequest):Observable<VentaResponse>{
        return this.http.post<VentaResponse>(`${this.url}/crear`,ventas);
    }

    eliminar(id:number):Observable<string>{
        return this.http.delete(`${this.url}/${id}`,{
            responseType:'text'
        });
    }
}
