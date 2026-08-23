import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConsolidadoResponse } from '../../models/consolidado/consolidado-response';
import { ConsolidadoSedeResponse } from '../../models/consolidado/consolidado-sede-response';
import { ConsolidadoProductoResponse } from '../../models/consolidado/consolidado-producto-response';

@Injectable({
  providedIn: 'root',
})
export class ConsolidadoServices {
  private url="http://localhost:8090/api/consolidado"

  constructor(private http:HttpClient){}

  obtenerConsolidado(): Observable<ConsolidadoResponse>{
    return this.http.get<ConsolidadoResponse>(this.url);
  }
  obtenerConsolidadoPorSede(): Observable<ConsolidadoSedeResponse[]>{
    return this.http.get<ConsolidadoSedeResponse[]>(`${this.url}/sedes`);
  }
  obtenerConsolidadoPorProducto(): Observable<ConsolidadoProductoResponse[]>{
    return this.http.get<ConsolidadoProductoResponse[]>(`${this.url}/productos`);
  }
}
