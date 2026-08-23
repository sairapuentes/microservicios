import { Component, OnInit, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { PermisosServices } from '../services/autenticacion/permisos-services';
import { SedeServices } from '../services/inventario/sede-services';
import { SedeResponse } from '../models/inventario/sede-response';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-layout',
  imports: [RouterModule],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout implements OnInit{

  rolUsuario= '';
  idSede = 0;
  nombreUsuario = '';
  correoUsuario = '';
  nombreSede = signal('');

  constructor(private router: Router, public permisos: PermisosServices, public sedeService: SedeServices, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.rolUsuario = localStorage.getItem('rol') || '';
    this.nombreUsuario = localStorage.getItem('nombreUsuario') || '';
    this.correoUsuario = localStorage.getItem('correo') || '';
    this.idSede = Number(localStorage.getItem('idSede') || '');
    this.obtenerNombreSede();
  }
 

  admin(): boolean {
    return this.rolUsuario === 'ADMIN';
  }

  usuarios(): boolean {
    return this.rolUsuario != 'BODEGA';
  }

  obtenerNombreSede(): void{
    if(this.rolUsuario === 'ADMIN'){
      this.nombreSede.set('Todas las sedes');
      return;
    }
    this.sedeService.listar().subscribe({
      next: (sedes: SedeResponse[]) => {
        const sede = sedes.find(s => s.idSede === this.idSede);
        if(sede){
          this.nombreSede.set(sede.nombreSede);
          this.cdr.detectChanges();
        }
      },
      error: (error) => {
        console.error('Error al obtener las sedes: ', error);
      }
    });
  }

  cerrarSesion():void{
    localStorage.removeItem('token');
    localStorage.removeItem('rol');
    localStorage.removeItem('idSede');
    localStorage.removeItem('isUsuario');
    localStorage.removeItem('nombreUsuario');
    localStorage.removeItem('correo');


    this.router.navigate(['/login']);
  }
}
