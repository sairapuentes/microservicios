import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { UsuarioRequest } from '../models/login/usuario-request';
import { UsuarioResponse } from '../models/login/usuario-response';
import { UsuarioServices } from '../services/usuario/usuario-services';
import { RolResponse } from '../models/login/rol-response';
import { RolServices } from '../services/login/rol-services';
import { SedeResponse } from '../models/inventario/sede-response';
import { SedeServices } from '../services/inventario/sede-services';

@Component({
  selector: 'app-usuarios',
  imports: [CommonModule, FormsModule],
  templateUrl: './usuarios.html',
  styleUrl: './usuarios.css',
})
export class Usuarios implements OnInit{
  usuarios = signal<UsuarioResponse[]>([]);
  usuarioFiltrados = signal<UsuarioResponse[]>([]);

  roles = signal<RolResponse[]>([]);
  sedes = signal<SedeResponse[]>([]);
  
  idUsuarioEditar: number = 0;
    
  usuario = signal<UsuarioRequest>({
    nombreUsuario: '',
    cedula: 0,
    correo: '',
    idRol: 0,
    password: '',
    idSede: 0
  
  });
    

  buscar = '';
  mostrarModal = signal(false);
  esActualizar = false;
  tituloModal = 'Nuevo Usuario'
  
  constructor(private usuarioService:UsuarioServices, private toastr: ToastrService, private rolService: RolServices, private sedeService: SedeServices,){}

  ngOnInit(): void {
    this.listarUsuarios();
    this.listarRoles();
    this.listarSedes();
  }

  listarUsuarios(){
    this.usuarioService.listar().subscribe({
      next:(data)=>{
        this.usuarios.set(data);
        this.usuarioFiltrados.set(data);
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error('No fue posible cargar los usuarios', 'Error');
      }
    });
  }
  
  listarRoles(){
    this.rolService.listar().subscribe({
      next:(data)=>{
        this.roles.set(data);
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error('No fue posible cargar los roles', 'Error');
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
        this.toastr.error('No fue posible cargar las sedes', 'Error');
      }
    });
  }
  
  crearUsuario(){
    this.usuarioService.crear(this.usuario()).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarUsuarios();
            this.toastr.success("Usuario registrado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible registrar el usuario", "Error");
        }
    });
  }
  
  actualizarUsuario(){
    this.usuarioService.actualizar(
        this.idUsuarioEditar,
        this.usuario()
    ).subscribe({
        next:(respuesta)=>{
            this.cerrarModal();
            this.listarUsuarios();
            this.toastr.success("Usuario actualizado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible actualizar", "Error");
        }
    });
  }

  nuevoUsuario() {
    this.esActualizar = false;
    this.tituloModal = 'Nuevo Usuario';
    this.idUsuarioEditar = 0;
    this.usuario.set({
      nombreUsuario: '',
      cedula: 0,
      correo: '',
      idRol: 0,
      password: '',
      idSede: 0
    });
    this.mostrarModal.set(true);
  }

  editarUsuario(id: number): void{
    this.esActualizar = true;
    this.idUsuarioEditar = id;
    this.tituloModal = 'Editar Usuario';
    this.usuarioService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.usuario.set({
          nombreUsuario: respuesta.nombreUsuario,
          cedula: respuesta.cedula,
          correo: respuesta.correo,
          idRol: this.obtenerIdRol(respuesta.nombreRol),
          password: '',
          idSede: respuesta.idSede
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
    this.usuario.set({
      nombreUsuario: '',
      cedula: 0,
      correo: '',
      idRol: 0,
      password: '',
      idSede: 0
    });
    this.esActualizar = false;
    this.idUsuarioEditar = 0;
  }

  guardarUsuario(){
    if(this.usuario().cedula==0){
      this.toastr.warning("Debe deligenciar la cedula", "Recuerde");
      return;
    }
    if(this.esActualizar){
      this.actualizarUsuario();
      return;
    }
    this.crearUsuario();
  }

  eliminarUsuario(id: number): void{

    if (!confirm('¿Desea eliminar este usuario?')) {
      return;
    }
    this.usuarioService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Usuario eliminado correctamente", "Éxito");
        this.listarUsuarios();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar el usuarioo", "Error");
      }
    });

  }

  buscarUsuario() {
    const texto = this.buscar.toLowerCase().trim();
    this.usuarioFiltrados.set(
      this.usuarios().filter(usuario =>
      usuario.nombreUsuario.toLowerCase().includes(texto) ||
      usuario.nombreRol.toLowerCase().includes(texto)
      )
    );
  }
  
  obtenerIdRol(nombreRol: string): number{
    const rol = this.roles().find(
      r => r.nombreRol === nombreRol
    );
    return rol ? rol.idRol : 0;
  }
}
