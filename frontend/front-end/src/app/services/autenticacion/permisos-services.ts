import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PermisosServices {
  private obtenerRol(): string{
    return localStorage.getItem('rol') || '';
  }
  
  rolAsignado(...roles: string[]): boolean{
    return roles.includes(this.obtenerRol());
  }

  crearProductos(): boolean{
    return this.rolAsignado('ADMIN');
  }
  editarProductos(): boolean{
    return this.rolAsignado('ADMIN');
  }
  eliminarProductos(): boolean{
    return this.rolAsignado('ADMIN');
  }
  crearInventario(): boolean{
    return this.rolAsignado('ADMIN', 'BODEGA');
  }
  editarInventario(): boolean{
    return this.rolAsignado('ADMIN', 'BODEGA');
  }
  eliminarInventario(): boolean{
    return this.rolAsignado('ADMIN');
  }
  puedeRestarStock(): boolean{
    return this.rolAsignado('ADMIN', 'BODEGA');
  }
  crearCliente(): boolean{
    return this.rolAsignado('ADMIN', 'CAJA');
  }
  editarCliente(): boolean{
    return this.rolAsignado('ADMIN', 'CAJA');
  }
  eliminarCliente(): boolean{
    return this.rolAsignado('ADMIN');
  }
  crearVenta(): boolean{
    return this.rolAsignado('CAJA', 'GERENTE');
  }
  editaVenta(): boolean{
    return this.rolAsignado('ADMIN', 'GERENTE');
  }
  eliminarVenta(): boolean{
    return this.rolAsignado('ADMIN', 'GERENTE');
  }
  adminUsuarios(): boolean{
    return this.rolAsignado('ADMIN');
  }
  adminRoles(): boolean{
    return this.rolAsignado('ADMIN');
  }
  adminSedes(): boolean{
    return this.rolAsignado('ADMIN');
  }
  consultarSedes(): boolean{
    return this.rolAsignado('ADMIN', 'GERENTE');
  }
}
