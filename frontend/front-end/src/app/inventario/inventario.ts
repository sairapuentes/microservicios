import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { InventarioRequest } from '../models/inventario/inventario-request';
import { InventarioResponse } from '../models/inventario/inventario-response';
import { InventarioRestarRequest } from '../models/inventario/inventario-restar-request';
import { InventarioServices } from '../services/inventario/inventario-services';
import { ProductosResponse } from '../models/productos/productos-response';
import { ProductosServices } from '../services/productos/productos-services';
import { SedeResponse } from '../models/inventario/sede-response';
import { SedeServices } from '../services/inventario/sede-services';
import { PermisosServices } from '../services/autenticacion/permisos-services';

@Component({
  selector: 'app-inventario',
  imports: [CommonModule, FormsModule],
  templateUrl: './inventario.html',
  styleUrl: './inventario.css',
})
export class Inventario implements OnInit{

  rolUsuario= '';
  idSedeUsuario = 0;
  inventarios = signal<InventarioResponse[]>([]);
  inventarioFiltrados = signal<InventarioResponse[]>([]);
  productos = signal<ProductosResponse[]>([]);
  sedes = signal<SedeResponse[]>([]);

  idInventarioEditar: number = 0;

  inventario = signal<InventarioRequest>({
    idProducto: 0,
    idSede: 0,
    cantidad: 0,
  });

  restarStock = signal<InventarioRestarRequest>({
    idProducto: 0,
    idSede: 0,
    cantidad: 0,
  });

  buscar = '';
  mostrarModal = signal(false);
  mostrarRestar = signal(false);
  esActualizar = false;
  tituloModal = 'Nuevo Stock'

  constructor(private inventarioService:InventarioServices, private toastr: ToastrService, private productoService:ProductosServices, private sedeService:SedeServices, public permisos: PermisosServices){}

  ngOnInit(): void {
    this.listarInventario();
    this.listarProductos();
    this.listarSedes();
    this.cargarPermisosUsuario();
  }

  cargarPermisosUsuario(): void {
    this.rolUsuario = localStorage.getItem('rol') || '';
    this.idSedeUsuario = Number(localStorage.getItem('idSede')) || 0;
  }

  listarInventario(): void{
    this.inventarioService.listar().subscribe({
      next:(data)=>{
        this.inventarios.set(data);
        this.inventarioFiltrados.set(data);
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error('No fue posible cargar el inventario', 'Error');
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

  crearInventario(){
    this.inventarioService.crear(this.inventario()).subscribe({
      next:(respuesta)=>{
        this.cerrarModal();
        this.listarInventario();
        this.toastr.success("Stock registrado correctamente", "Éxito");
      },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible registrar el stock", "Error");
        }
    });
  }

  actualizarInventario(){
    this.inventarioService.actualizar(
      this.idInventarioEditar,
      this.inventario()
    ).subscribe({
      next:(respuesta)=>{
        this.cerrarModal();
        this.listarInventario();
        this.toastr.success("Stock actualizado correctamente", "Éxito");
      },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible actualizar stock", "Error");
        }
    });
  }

  nuevoInventario(){
    this.esActualizar = false;
    this.tituloModal = 'Nuevo Stock';
    this.inventario.set({
      idProducto: 0,
      idSede: this.rolUsuario === 'BODEGA'
        ? this.idSedeUsuario
        : 0,
      cantidad: 0
    });
    this.mostrarModal.set(true);
  }

  editarInventario(id: number){
    this.esActualizar = true;
    this.idInventarioEditar = id;
    this.tituloModal = 'Editar Stock';
    this.inventarioService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.inventario.set({
          idProducto: respuesta.idProducto,
          idSede: respuesta.idSede,
          cantidad: respuesta.cantidad
        });
        this.mostrarModal.set(true);
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error('No fue posible actualizar el inventario', 'Error');
      }  
    });
  }

  cerrarModal(){
    this.mostrarModal.set(false);
    this.inventario.set({
      idProducto: 0,
      idSede: 0,
      cantidad: 0
    });
    this.esActualizar = false;
    this.idInventarioEditar = 0;
  }

  guardarInventario(){
    const datos = this.inventario();
    if(datos.idProducto <= 0){
      this.toastr.warning("Debe seleccionar un producto", "Recuerda");
      return;
    }
    if(datos.idSede <= 0){
      this.toastr.warning("Debe seleccionar una sede", "Recuerda");
      return;
    }
    if(datos.cantidad <= 0){
      this.toastr.warning("La cantidad debe ser mayor que cero", "Recuerda");
      return;
    }
    if(this.esActualizar){
      this.actualizarInventario();
      return;
    }
    this.crearInventario();
  }

  eliminarInventario(id: number) {

    if (!confirm('¿Desea eliminar este stock?')) {
      return;
    }
    this.inventarioService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Stock eliminado correctamente", "Éxito");
        this.listarInventario();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar el Stock", "Error");
      }
    });

  }

  buscarInventario(): void{
    const texto = this.buscar.toLowerCase().trim();
    this.inventarioFiltrados.set(
      this.inventarios().filter(inventario =>{
        const nombreProducto = this.obtenerNombreProducto(inventario.idProducto).toLowerCase();
        const nombreSede = inventario.nombreSede.toLocaleLowerCase();
        return(
          nombreProducto.includes(texto) ||
          nombreSede.includes(texto)
        );
      })
    );
  }

  abrirModalRestarStock(){
    this.restarStock.set({
      idProducto: 0,
      idSede: this.rolUsuario === 'BODEGA'
        ? this.idSedeUsuario
        : 0,
      cantidad: 0
    });
    this.mostrarRestar.set(true);
  }

  cerrarModalRestarStock(){
    this.mostrarRestar.set(false);
     this.restarStock.set({
      idProducto: 0,
      idSede: 0,
      cantidad: 0
    });
  }

  restarInventario(){
    const datos = this.restarStock();
    if(datos.idProducto <= 0){
      this.toastr.warning("Debe seleccionar un producto", "Recuerda");
      return;
    }
    if(datos.idSede <= 0){
      this.toastr.warning("Debe seleccionar una sede", "Recuerda");
      return;
    }
    if(datos.cantidad <= 0){
      this.toastr.warning("La cantidad debe ser mayor que cero", "Recuerda");
      return;
    }
    // if(this.esActualizar){
    //   this.actualizarInventario();
    //   return;
    // }
    this.inventarioService.restarStock(datos).subscribe({
      next:()=>{
        this.cerrarModalRestarStock();
        this.listarInventario();
        this.toastr.success("Stock actualizado correctamente", "Éxito");
      },
      error:(err) =>{
        this.toastr.error('No fue posible restar el stock', 'Error');
      }
    });

  }
  obtenerNombreProducto(id: number): string {
    const producto = this.productos().find(p => p.idProducto === id);
    return producto ? producto.nombreProducto : '';
  }

}
