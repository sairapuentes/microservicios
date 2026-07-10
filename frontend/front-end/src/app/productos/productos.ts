import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OnInit } from '@angular/core';
import { ProductosRequest } from '../models/productos/productos-request';
import { ProductosResponse } from '../models/productos/productos-response';
import { ProductosServices } from '../services/productos/productos-services';
import { CategoriaResponse } from '../models/categoria/categoria-response';
import { CategoriaServices } from '../services/categoria/categoria-services';

@Component({
  selector: 'app-productos',
  imports: [CommonModule, FormsModule],
  templateUrl: './productos.html',
  styleUrl: './productos.css',
})
export class Productos implements OnInit{

  productos:ProductosResponse[]=[];
  categorias:CategoriaResponse[]=[];
  productosFiltrados: ProductosResponse[]=[];

  idProductoEditar: number = 0;
  
  producto:ProductosRequest={
    idCategoria: 0,
    nombreProducto: '',
    precioCompra: 0,
    ivaCompra: 0
  };
  
  constructor(private productoService:ProductosServices, private categoriaService:CategoriaServices){}

  ngOnInit(): void {
    this.listarProductos();
    this.listarCategorias();
  }

  listarProductos(){
    this.productoService.listar().subscribe({
      next:(data)=>{
        this.productos=[...data]; //[...] crea una copia y evita problemas de deteccion de cambios
        this.productosFiltrados = [...data];
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  listarCategorias(){
    this.categoriaService.listar().subscribe({
      next:(data)=>{
        this.categorias=data;
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  crearProducto(){
    this.productoService.crear(this.producto).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarProductos();
            console.log("Producto registrado correctamente");
        },
        error:(err)=>{
            console.error(err);
            alert("No fue posible registrar el producto");
        }
    });
  }

  actualizarProducto(){
    this.productoService.actualizar(
        this.idProductoEditar,
        this.producto
    ).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarProductos();
            console.log("Producto actualizado correctamente");
        },
        error:(err)=>{
            console.error(err);
            alert("No fue posible actualizar");
        }
    });
  }

  buscar = '';
  mostrarModal = false;
  esActualizar = false;
  tituloModal = 'Nuevo Producto'

  nuevoProducto() {
    this.esActualizar = false;
    this.tituloModal = 'Nuevo Producto';

    this.producto= {
      idCategoria: 0,
      nombreProducto: '',
      precioCompra: 0,
      ivaCompra: 0
    } as ProductosRequest;
    

    this.mostrarModal = true;
  }

  editarProducto(id: number) {
    this.esActualizar = true;
    this.idProductoEditar = id;
    this.tituloModal = 'Editar Producto';
    this.productoService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.producto={
          idCategoria: respuesta.idCategoria,
          nombreProducto:respuesta.nombreProducto,
          precioCompra:respuesta.precioCompra,
          ivaCompra:respuesta.ivaCompra
        };
        this.mostrarModal = true;
      },
      error:(err)=>{
        console.error(err);
      }
    });
    
  }

  cerrarModal(){
    this.mostrarModal=false;
    this.producto= {
      idCategoria: 0,
      nombreProducto: '',
      precioCompra: 0,
      ivaCompra: 0
    };
    this.esActualizar = false;
    this.idProductoEditar = 0;
  }

  guardarProducto(){
    if(this.producto.idCategoria==0){
      alert("Debe seleccionar una categoria");
      return;
    }
    if(this.esActualizar){
      this.actualizarProducto();
      return;
    }
    this.crearProducto();
  }

  eliminarProducto(id: number) {

    const confirmar = confirm('¿Desea eliminar este producto?');

    if (!confirm("¿Desea eliminar el producto?")) {
      return;
    }
    this.productoService.eliminar(id).subscribe({
      next:()=>{
        alert("Producto eliminado correctamente");
        this.listarProductos();
      },
      error:(err)=>{
        console.error(err);
        alert("No fue posile eliminar el producto");
      }
    });

  }

  buscarProducto() {
    const texto = this.buscar.toLowerCase();
    this.productosFiltrados = this.productos.filter(producto =>
      producto.nombreProducto.toLowerCase().includes(texto) ||
      producto.nombreCategoria.toLowerCase().includes(texto)
    );
  }
}
