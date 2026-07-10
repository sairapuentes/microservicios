import { Routes } from '@angular/router';

import { Login } from './login/login';
import { Layout } from './layout/layout';

import { Dashboard } from './dashboard/dashboard';
import { Productos } from './productos/productos';
import { Clientes } from './clientes/clientes';
import { Ventas } from './ventas/ventas';
import { Usuarios } from './usuarios/usuarios';
import { Inventario } from './inventario/inventario';
import { Configuracion } from './configuracion/configuracion';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: Login
  },

  {
    path: '',
    component: Layout,
    children: [

      {
        path: 'dashboard',
        component: Dashboard
      },

      {
        path: 'productos',
        component: Productos
      },

      {
        path: 'clientes',
        component: Clientes
      },

      {
        path: 'ventas',
        component: Ventas
      },

      {
        path: 'usuarios',
        component: Usuarios
      },

      {
        path: 'inventario',
        component: Inventario
      },

      {
        path: 'configuracion',
        component: Configuracion
      }

    ]

  },

  {
    path: '**',
    redirectTo: 'login'
  }

];