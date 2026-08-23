import { Component, OnInit, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProductosServices } from '../services/productos/productos-services';
import { ClienteServices } from '../services/clientes/cliente-services';
import { InventarioServices } from '../services/inventario/inventario-services';
import { VentaServices } from '../services/ventas/venta-services';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit{
  totalProductos = signal(0);
  totalClientes = signal(0);
  totalVentas = signal(0);
  totalStock = signal(0);

  constructor(private productoService: ProductosServices, private clienteService: ClienteServices, private inventarioService: InventarioServices, private ventaService: VentaServices){}

  ngOnInit(): void {
    this.cargarDashboard();
  }

  cargarDashboard(): void{
    this.productoService.listar().subscribe({
      next: (data) => {
        this.totalProductos.set(data.length);
      },
      error: (err) => {
        console.error('Error cargando productos: ', err);
      }
    });
    this.clienteService.listar().subscribe({
      next: (data) => {
        this.totalClientes.set(data.length);
      },
      error: (err) => {
        console.error('Error cargando clientes: ', err);
      }
    });
    this.ventaService.listar().subscribe({
      next: (data) => {
        this.totalVentas.set(data.length);
      },
      error: (err) => {
        console.error('Error cargando ventas: ', err);
      }
    });
    this.inventarioService.listar().subscribe({
      next: (data) => {
        const stock = data.reduce(
          (total, inventario) => total + inventario.cantidad,
          0
        );
        this.totalStock.set(stock);
      },
      error: (err) => {
        console.error('Error cargando inventario: ', err);
      }
    });
  }
}
