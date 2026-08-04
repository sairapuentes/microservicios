import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { VentaServices } from '../services/ventas/venta-services';
import { VentaRequest } from '../models/ventas/venta-request';
import { VentaResponse } from '../models/ventas/venta-response';
import { ClientesResponse } from '../models/clientes/clientes-response';
import { ProductosResponse } from '../models/productos/productos-response';
import { SedeResponse } from '../models/inventario/sede-response';
import { ClienteServices } from '../services/clientes/cliente-services';
import { ProductosServices } from '../services/productos/productos-services';
import { SedeServices } from '../services/inventario/sede-services';

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
  sedes = signal<SedeResponse[]>([]);

  venta = signal<VentaRequest>({
    idVenta: 0,
    idCliente: 0,
    idCiudad: 0,
    idProducto: 0,
    cantidad: 0,
    idSede: 0
  });

  buscar = '';
  mostrarModal = signal(false);
  tituloModal = 'Nuevo Venta'

  constructor(private ventaService:VentaServices, private toastr: ToastrService, private clienteService: ClienteServices, private productoService: ProductosServices, private sedeService: SedeServices){}

  ngOnInit(): void{
    this.listarVentas();
    this.listarClientes();
    this.listarProductos();
    this.listarSedes();
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

  listarProductos(){
    this.productoService.listar().subscribe({
      next:(data)=>{
        this.productos.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  listarSedes(){
    this.sedeService.listar().subscribe({
      next:(data)=>{
        this.sedes.set(data);
      },
      error:(err)=>{
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
            this.toastr.error(err.error.mensaje, "Error");
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
      cantidad: 0,
      idSede: 0
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
      cantidad: 0,
      idSede: 0
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
    if(this.venta().idSede === 0){
      this.toastr.warning("Seleccione un sede");
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
        venta.nombreSede.toLowerCase().includes(texto) ||
        venta.nombreProducto.toLowerCase().includes(texto)
      )
    );
  }
}
