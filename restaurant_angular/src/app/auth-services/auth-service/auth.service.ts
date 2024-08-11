import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = ["http://localhost:9090/"]

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  signup(signupRequest: any): Observable<any>{
    return this.http.post<[]>(BASIC_URL + "api/v1/auth/signup",signupRequest)
  }

  login(loginRequest: any): Observable<any>{
    return this.http.post<[]>(BASIC_URL + "api/v1/auth/login",loginRequest)
  }

}
