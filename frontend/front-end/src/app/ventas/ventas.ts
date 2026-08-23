import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { VentaServices } from '../services/ventas/venta-services';
import { VentaRequest } from '../models/ventas/venta-request';
import { VentaResponse } from '../models/ventas/venta-response';
import { ClientesResponse } from '../models/clientes/clientes-response';
import { ProductosResponse } from '../models/productos/productos-response';
import { InventarioServices } from '../services/inventario/inventario-services';
import { ClienteServices } from '../services/clientes/cliente-services';
import { InventarioResponse } from '../models/inventario/inventario-response';
import { ProductosServices } from '../services/productos/productos-services';
import { PermisosServices } from '../services/autenticacion/permisos-services';


@Component({
  selector: 'app-ventas',
  imports: [CommonModule, FormsModule],
  templateUrl: './ventas.html',
  styleUrl: './ventas.css',
})
export class Ventas implements OnInit{
  ventas = signal<VentaResponse[]>([]);
  ventasFiltradas = signal<VentaResponse[]>([]);
  clientes = signal<ClientesResponse[]>([]);
  productos = signal<ProductosResponse[]>([]);
  inventarios = signal<InventarioResponse[]>([]);

  venta = signal<VentaRequest>({
    idVenta: 0,
    idCliente: 0,
    idCiudad: 0,
    idProducto: 0,
    cantidad: 0
  });

  rolUsuario= '';
  buscar = '';
  mostrarModal = signal(false);
  tituloModal = 'Nuevo Venta'

  constructor(private ventaService:VentaServices, private toastr: ToastrService, private clienteService: ClienteServices, private productoService: ProductosServices, private inventarioService: InventarioServices, public permisos: PermisosServices){}

  ngOnInit(): void{
    this.listarVentas();
    this.listarClientes();
    this.listarProductosDisponible();
    this.cargarPermisosUsuario();
  }

  cargarPermisosUsuario(): void {
    this.rolUsuario = localStorage.getItem('rol') || '';
  }

  listarVentas(){
    this.ventaService.listar().subscribe({
      next:(data)=>{
        this.ventas.set(data);
        this.ventasFiltradas.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  listarClientes(){
    this.clienteService.listar().subscribe({
      next:(data)=>{
        this.clientes.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  listarProductosDisponible(){
    this.inventarioService.listar().subscribe({
      next: (inventarios) => {
        const disponibles = inventarios.filter(inventario => inventario.cantidad > 0);
        this.productoService.listar().subscribe({
          next: (productos) =>{
            const productosDisponibles = productos.filter(producto =>
              disponibles.some(inventario => inventario.idProducto === producto.idProducto)
            );
            this.productos.set(productosDisponibles);
          },
          error: (err) => {
            console.error(err);
          }
        });
      },
      error: (err) => {
        console.error(err);
      }
     });
  }

  crearVenta(){
    this.ventaService.crear(this.venta()).subscribe({
      next:(respuesta)=>{
        this.cerrarModal();
        this.listarVentas();
        this.toastr.success("Venta registrada correctamente", "Éxito");
      },
       error:(err)=>{
            console.error(err);
            const mensaje = err.error?.mensaje || "No tiene permisos para realizar estaoperación";
            this.toastr.error(mensaje, "Error");
        }
    });
  }

  nuevaVenta(){
    this.tituloModal = 'Nueva Venta';
    this.venta.set({
      idVenta: 0,
      idCliente: 0,
      idCiudad: 0,
      idProducto: 0,
      cantidad: 0
    });
    this.mostrarModal.set(true);
  }

  cerrarModal(){
    this.mostrarModal.set(false);
    this.venta.set({
      idVenta: 0,
      idCliente: 0,
      idCiudad: 0,
      idProducto: 0,
      cantidad: 0
    });
  }

  guardarVenta(){
    if(this.venta().idCliente === 0){
      this.toastr.warning("Seleccione un cliente");
      return;
    }
    if(this.venta().idProducto === 0){
      this.toastr.warning("Seleccione un producto");
      return;
    }

    if(this.venta().cantidad <= 0){
      this.toastr.warning("La cantidad debe ser mayor que cero");
      return;
    }
    this.crearVenta();
  }

  eliminarVenta(id: number) {

    if (!confirm('¿Desea eliminar esta venta?')) {
      return;
    }
    this.ventaService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Venta eliminada correctamente", "Éxito");
        this.listarVentas();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar la venta", "Error");
      }
    });

  }

  buscarVenta(): void{
    const texto = this.buscar.toLowerCase().trim();
    this.ventasFiltradas.set(
      this.ventas().filter(venta =>
        venta.nombreCliente.toLowerCase().includes(texto) ||
        venta.nombreProducto.toLowerCase().includes(texto)
      )
    );
  }
}
