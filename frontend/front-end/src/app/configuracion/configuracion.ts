import { Component, OnInit, signal} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { RolResponse } from '../models/login/rol-response';
import { RolRequest } from '../models/login/rol-request';
import { RolServices } from '../services/login/rol-services';
import { SedeResponse } from '../models/inventario/sede-response';
import { SedeRequest } from '../models/inventario/sede-request';
import { SedeServices } from '../services/inventario/sede-services';

@Component({
  selector: 'app-configuracion',
  imports: [CommonModule, FormsModule],
  templateUrl: './configuracion.html',
  styleUrl: './configuracion.css',
})
export class Configuracion implements OnInit{
  
  //Rol

  roles = signal<RolResponse[]>([]);
  rolesFiltrados = signal<RolResponse[]>([]);

  idRolEditar = 0;

  rol = signal<RolRequest>({
    nombreRol:''
  });

  buscarRol = '';
  mostrarModalRol = signal(false);
  esActualizarRol = false;
  tituloModalRol = 'Nuevo Rol'

  //Sede

  sedes = signal<SedeResponse[]>([]);
  sedesFiltradas = signal<SedeResponse[]>([]);

  idSedeEditar = 0;

  sede = signal<SedeRequest>({
    nombreSede:'',
    direccion:'',
    ciudad:''
  });

  buscarSede = '';
  mostrarModalSede = signal(false);
  esActualizarSede = false;
  tituloModalSede = 'Nuevo Sede'

  constructor(private rolService:RolServices, private sedeService:SedeServices, private toastr: ToastrService){}

  ngOnInit(): void {
    this.listarRoles();
    this.listarSedes();
  }

  //Rol
  listarRoles(){
    this.rolService.listar().subscribe({
      next:(data)=>{
        this.roles.set(data);
        this.rolesFiltrados.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  crearRol(){
    this.rolService.crear(this.rol()).subscribe({
        next:(respuesta)=>{
            this.cerrarModalRol();
            this.listarRoles();
            this.toastr.success("Rol registrado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible registrar el rol", "Error");
        }
    });
  }

  actualizarRol(){
    this.rolService.actualizar(
        this.idRolEditar,
        this.rol()
    ).subscribe({
        next:(respuesta)=>{
            this.cerrarModalRol();
            this.listarRoles();
            this.toastr.success("Rol actualizado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible actualizar", "Error");
        }
    });
  }

  nuevoRol() {
    this.esActualizarRol = false;
    this.tituloModalRol = 'Nuevo Rol';
    this.rol.set({
      nombreRol: ''
    });
    this.mostrarModalRol.set(true);
  }

  editarRol(id: number) {
    this.esActualizarRol = true;
    this.idRolEditar = id;
    this.tituloModalRol = 'Editar Rol';
    this.rolService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.rol.set({
          nombreRol:respuesta.nombreRol
        });
        this.mostrarModalRol.set(true);
      },
      error:(err)=>{
        console.error(err);
      }
    });
    
  }

  cerrarModalRol(){
    this.mostrarModalRol.set(false);
    this.rol.set({
      nombreRol: ''
    });
    this.esActualizarRol = false;
    this.idRolEditar = 0;
  }

  guardarRol(){
    if(this.rol().nombreRol == ''){
      this.toastr.warning("Debe asignar un rol", "Recuerde");
      return;
    }
    if(this.esActualizarRol){
      this.actualizarRol();
      return;
    }
    this.crearRol();
  }

  eliminarRol(id: number) {

    if (!confirm('¿Desea eliminar este rol?')) {
      return;
    }
    this.rolService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Rol eliminado correctamente", "Éxito");
        this.listarRoles();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar el rol", "Error");
      }
    });

  }

  buscarRoles() {
    const texto = this.buscarRol.toLowerCase();
    this.rolesFiltrados.set(
      this.roles().filter(rol =>
      rol.nombreRol.toLowerCase().includes(texto) 
      )
    );
  }


  //Sede
  listarSedes(){
    this.sedeService.listar().subscribe({
      next:(data)=>{
        this.sedes.set(data);
        this.sedesFiltradas.set(data);
      },
      error:(err)=>{
        console.error(err);
      }
    });
  }

  crearSede(){
    this.sedeService.crear(this.sede()).subscribe({
        next:(respuesta)=>{
            this.cerrarModalSede();
            this.listarSedes();
            this.toastr.success("Sedes registrado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible registrar la sede", "Error");
        }
    });
  }

  actualizarSede(){
    this.sedeService.actualizar(
        this.idSedeEditar,
        this.sede()
    ).subscribe({
        next:(respuesta)=>{
            this.cerrarModalSede();
            this.listarSedes();
            this.toastr.success("Sede actualizado correctamente", "Éxito");
        },
        error:(err)=>{
            console.error(err);
            this.toastr.error("No fue posible actualizar", "Error");
        }
    });
  }

  nuevaSede() {
    this.esActualizarSede = false;
    this.tituloModalSede = 'Nuevo Sede';
    this.sede.set({
      nombreSede:'',
      direccion:'',
      ciudad:''
    });
    this.mostrarModalSede.set(true);
  }

  editarSede(id: number) {
    this.esActualizarSede = true;
    this.idSedeEditar = id;
    this.tituloModalSede = 'Editar Sede';
    this.sedeService.buscarPorId(id).subscribe({
      next:(respuesta)=>{
        this.sede.set({
          nombreSede: respuesta.nombreSede,
          direccion: respuesta.direccion,
          ciudad: respuesta.ciudad
        });
        this.mostrarModalSede.set(true);
      },
      error:(err)=>{
        console.error(err);
      }
    });
    
  }

  cerrarModalSede(){
    this.mostrarModalSede.set(false);
    this.sede.set({
      nombreSede:'',
      direccion:'',
      ciudad:''
    });
    this.esActualizarSede = false;
    this.idSedeEditar = 0;
  }

  guardarSede(){
    if(this.sede().nombreSede == ''){
      this.toastr.warning("Debe seleccionar una sedes", "Recuerde");
      return;
    }
    if(this.esActualizarSede){
      this.actualizarSede();
      return;
    }
    this.crearSede();
  }

  eliminarSede(id: number) {

    if (!confirm('¿Desea eliminar esta sede?')) {
      return;
    }
    this.sedeService.eliminar(id).subscribe({
      next:()=>{
        this.toastr.success("Sede eliminado correctamente", "Éxito");
        this.listarSedes();
      },
      error:(err)=>{
        console.error(err);
        this.toastr.error("No fue posile eliminar la sede", "Error");
      }
    });

  }

  buscarSedes() {
    const texto = this.buscarSede.toLowerCase();
    this.sedesFiltradas.set(
      this.sedes().filter(sede =>
      sede.nombreSede.toLowerCase().includes(texto) ||
      sede.ciudad.toLowerCase().includes(texto)
      )
    );
  }
}
