import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ClienteServices } from '../services/clientes/cliente-services';
import { ClientesRequest } from '../models/clientes/clientes-request';
import { ClientesResponse } from '../models/clientes/clientes-response';
import { ToastrService } from 'ngx-toastr';
import { PermisosServices } from '../services/autenticacion/permisos-services';

@Component({
  selector: 'app-clientes',
  imports: [CommonModule, FormsModule],
  templateUrl: './clientes.html',
  styleUrl: './clientes.css',
})
export class Clientes implements OnInit{

  rolUsuario= '';
  idSedeUsuario = 0;
  clientes = signal<ClientesResponse[]>([]);
  clientesFiltrados = signal<ClientesResponse[]>([]);

  idClienteEditar: number = 0;

  cliente = signal<ClientesRequest>({
    idCliente: 0,
    nombreCliente: '',
    telefonoCliente: '',
    emailCliente: ''
  });

  buscar = '';
  mostrarModal = signal(false);
  esActualizar = false;
  tituloModal = 'Nuevo Cliente'

  constructor(private clienteService:ClienteServices, private toastr: ToastrService, public permisos: PermisosServices){}
  ngOnInit(): void {
    this.listarClientes();
    this.cargarPermisosUsuario();
  }
  
  cargarPermisosUsuario(): void {
    this.rolUsuario = localStorage.getItem('rol') || '';
    this.idSedeUsuario = Number(localStorage.getItem('idSede')) || 0;
  }
  
  listarClientes(){
    this.clienteService.listar().subscribe({
      next:(data)=>{
        this.clientes.set(data);
        this.clientesFiltrados.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  crearCliente(){
    this.clienteService.crear(this.cliente()).subscribe({
      next:(respuesta)=>{
        this.cerrarModal();
        this.listarClientes();
        this.toastr.success("Cliente registrado correctamente", "Éxito");
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posible registrar el cliente", "Error");
      }
    });
  }

  actualizarCliente(){
    this.clienteService.actualizar(
      this.idClienteEditar,
      this.cliente()
    ).subscribe({
      next:(respuesta)=>{
        this.cerrarModal();
        this.listarClientes();
        this.toastr.success("Cliente actualizado correctamente", "Éxito");
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posible actualizar", "Error");
      }
    });
  }

  nuevoCliente(){
    this.esActualizar = false;
    this.tituloModal = 'Nuevo Cliente';
    this.cliente.set({
      idCliente: 0,
      nombreCliente: '',
      telefonoCliente: '',
      emailCliente: ''
    });
    this.mostrarModal.set(true);
  }

  buscarCliente(){
    const texto = this.buscar.toLowerCase();
    this.clientesFiltrados.set(
      this.clientes().filter(cliente=>
        cliente.idCliente.toString().includes(texto) ||
        cliente.nombreCliente.toLowerCase().includes(texto)
      )
    );
  }

  editarCliente(id: number){
    this.esActualizar = true;
    this.idClienteEditar = id;
    this.tituloModal = 'Editar Cliente';
    this.clienteService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.cliente.set({
          idCliente: respuesta.idCliente,
          nombreCliente: respuesta.nombreCliente,
          telefonoCliente: respuesta.telefonoCliente,
          emailCliente: respuesta.emailCliente
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
    this.cliente.set({
      idCliente: 0,
      nombreCliente: '',
      telefonoCliente: '',
      emailCliente: ''
    });
    this.esActualizar = false;
    this.idClienteEditar = 0;
  }

  guardarCliente(){
    if(this.esActualizar){
      this.actualizarCliente();
      return;
    }
    this.crearCliente();
  }

  eliminarCliente(id: number){
    if(!confirm('¿Desea eliminar este cliente?')){
      return;
    }
    this.clienteService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Cliente eliminado correctamente", "Éxito");
        this.listarClientes();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posible eliminar el cliente", "Error")
      }
    });
  }
}
