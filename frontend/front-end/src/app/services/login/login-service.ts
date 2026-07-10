import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = 'http://localhost:8090/api/auth/login';

  constructor(private http: HttpClient) {}

  login(datos: any): Observable<any> {
    return this.http.post(this.url, datos);
  }

}