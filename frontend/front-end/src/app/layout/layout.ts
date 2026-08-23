import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { PermisosServices } from '../services/autenticacion/permisos-services';


@Component({
  selector: 'app-layout',
  imports: [RouterModule],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout {

  rolUsuario= '';

  constructor(private router: Router, public permisos: PermisosServices) {
    this.rolUsuario = localStorage.getItem('rol') || '';
  }
 

  admin(): boolean {
    return this.rolUsuario === 'ADMIN';
  }

  usuarios(): boolean {
    return this.rolUsuario != 'BODEGA';
  }

  cerrarSesion():void{
    localStorage.removeItem('token');
    localStorage.removeItem('rol');
    localStorage.removeItem('idSede');

    this.router.navigate(['/login']);
  }
}
