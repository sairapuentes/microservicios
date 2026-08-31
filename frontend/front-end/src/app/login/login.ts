import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { LoginService } from '../services/login/login-service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  correo = '';
  password = '';

  mostrarPassword = false;

  constructor(
    private router: Router,
    private loginService: LoginService
  ) {}

  ingresar() {

    const datos = {
      correo: this.correo,
      password: this.password
    };

    this.loginService.login(datos).subscribe({
      next: (respuesta) => {
        console.log('Login exitoso', respuesta);
        localStorage.setItem('token', respuesta.token);
        localStorage.setItem('rol', respuesta.nombreRol);
        localStorage.setItem('idSede', respuesta.idSede.toString());
        localStorage.setItem('idUsuario', respuesta.idUsuario.toString());
        localStorage.setItem('nombreUsuario', respuesta.nombreUsuario);
        localStorage.setItem('correo', respuesta.correo);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        alert("Correo o contraseña incorrectos");
        console.error(error);
      }

    });

  }

}
