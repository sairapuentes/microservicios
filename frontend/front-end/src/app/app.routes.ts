import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Productos } from './productos/productos';
import { Clientes } from './clientes/clientes';
import { Ventas } from './ventas/ventas';

export const routes: Routes = [{path: 'login', component: Login},{path: 'productos', component: Productos},{path: 'clientes', component: Clientes},{path: 'ventas', component: Ventas}];
