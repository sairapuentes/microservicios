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
        console.log(respuesta);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        alert("Correo o contraseña incorrectos");
        console.error(error);
      }

    });

  }

}