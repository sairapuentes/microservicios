import { Component, OnInit, signal} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProductosRequest } from '../models/productos/productos-request';
import { ProductosResponse } from '../models/productos/productos-response';
import { ProductosServices } from '../services/productos/productos-services';
import { CategoriaResponse } from '../models/categoria/categoria-response';
import { CategoriaServices } from '../services/categoria/categoria-services';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-productos',
  imports: [CommonModule, FormsModule],
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class Productos implements OnInit{

  productos = signal<ProductosResponse[]>([]);
  categorias = signal<CategoriaResponse[]>([]);
  productosFiltrados = signal<ProductosResponse[]>([]);

  idProductoEditar: number = 0;
  
  producto = signal<ProductosRequest>({
    idCategoria: 0,
    nombreProducto: '',
    precioCompra: 0,
    ivaCompra: 0
  
  });
    

  buscar = '';
  mostrarModal = signal(false);
  esActualizar = false;
  tituloModal = 'Nuevo Producto'
  
  constructor(private productoService:ProductosServices, private categoriaService:CategoriaServices, private toastr: ToastrService){}

  ngOnInit(): void {
    this.listarProductos();
    this.listarCategorias();
  }

  listarProductos(){
    this.productoService.listar().subscribe({
      next:(data)=>{
        this.productos.set(data); //notificar cambios
        this.productosFiltrados.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  listarCategorias(){
    this.categoriaService.listar().subscribe({
      next:(data)=>this.categorias.set(data),
      error:(err)=>console.error(err)
    });
  }

  crearProducto(){
    this.productoService.crear(this.producto()).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarProductos();
            this.toastr.success("Producto registrado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible registrar el producto", "Error");
        }
    });
  }

  actualizarProducto(){
    this.productoService.actualizar(
        this.idProductoEditar,
        this.producto()
    ).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarProductos();
            this.toastr.success("Producto actualizado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible actualizar", "Error");
        }
    });
  }

  nuevoProducto() {
    this.esActualizar = false;
    this.tituloModal = 'Nuevo Producto';
    this.producto.set({
      idCategoria: 0,
      nombreProducto: '',
      precioCompra: 0,
      ivaCompra: 0
    });
    this.mostrarModal.set(true);
  }

  editarProducto(id: number) {
    this.esActualizar = true;
    this.idProductoEditar = id;
    this.tituloModal = 'Editar Producto';
    this.productoService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.producto.set({
          idCategoria: respuesta.idCategoria,
          nombreProducto:respuesta.nombreProducto,
          precioCompra:respuesta.precioCompra,
          ivaCompra:respuesta.ivaCompra
        });
        this.mostrarModal.set(true);
      },
      error:(err)=>{
        console.error(err);
      }
    });
    
  }

  cerrarModal(){
    this.mostrarModal.set(false);
    this.producto.set({
      idCategoria: 0,
      nombreProducto: '',
      precioCompra: 0,
      ivaCompra: 0
    });
    this.esActualizar = false;
    this.idProductoEditar = 0;
  }

  guardarProducto(){
    if(this.producto().idCategoria==0){
      this.toastr.warning("Debe seleccionar una categoria", "Recuerde");
      return;
    }
    if(this.esActualizar){
      this.actualizarProducto();
      return;
    }
    this.crearProducto();
  }

  eliminarProducto(id: number) {

    if (!confirm('¿Desea eliminar este producto?')) {
      return;
    }
    this.productoService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Producto eliminado correctamente", "Éxito");
        this.listarProductos();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar el producto", "Error");
      }
    });

  }

  buscarProducto() {
    const texto = this.buscar.toLowerCase();
    this.productosFiltrados.set(
      this.productos().filter(producto =>
      producto.nombreProducto.toLowerCase().includes(texto) ||
      producto.nombreCategoria.toLowerCase().includes(texto)
      )
    );
  }
}
