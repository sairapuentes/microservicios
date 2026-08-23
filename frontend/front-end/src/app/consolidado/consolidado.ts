import { Component, OnInit } from '@angular/core';
import { ConsolidadoServices } from '../services/consolidado/consolidado-services';
import { ConsolidadoResponse } from '../models/consolidado/consolidado-response';
import { ConsolidadoSedeResponse } from '../models/consolidado/consolidado-sede-response';
import { ConsolidadoProductoResponse } from '../models/consolidado/consolidado-producto-response';
import { DecimalPipe } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-consolidado',
  imports: [DecimalPipe],
  templateUrl: './consolidado.html',
  styleUrl: './consolidado.css',
})
export class Consolidado implements OnInit{
  consolidado: ConsolidadoResponse | null = null;
  consolidadoSedes: ConsolidadoSedeResponse[] = [];
  consolidadoProductos: ConsolidadoProductoResponse[] = [];

  constructor(private consolidadoService: ConsolidadoServices, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    this.cargarConsolidado();
    this.cargarConsolidadoSedes();
    this.cargarConsolidadoProductos();
  }

  cargarConsolidado(): void{
    this.consolidadoService.obtenerConsolidado().subscribe({
      next: (data) => {
        this.consolidado = data;
        console.log('Consolidado', data);
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error al obtener consolidado: ', error);
      }
    });
  }
  cargarConsolidadoSedes(): void{
    this.consolidadoService.obtenerConsolidadoPorSede().subscribe({
      next: (data) => {
        this.consolidadoSedes = data;
        console.log('Consolidado por sede', data);
        console.log('Cantidad sede', this.consolidadoSedes.length);
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error al obtener consolidado por sede: ', error);
      }
    });

  }
  cargarConsolidadoProductos(): void{
    this.consolidadoService.obtenerConsolidadoPorProducto().subscribe({
      next: (data) => {
        this.consolidadoProductos = data;
        console.log('Consolidado por producto', data);
        console.log('Cantidad producto', this.consolidadoProductos.length);
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error al obtener consolidado por producto: ', error);
      }
    });
  }
}
