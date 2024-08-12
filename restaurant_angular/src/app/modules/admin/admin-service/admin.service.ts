import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth-services/storage-service/storage.service';

const BASIC_URL = ["http://localhost:9090/"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postCategory(categoryDto: any):Observable<any>{
    return this.http.post<[]>(BASIC_URL+ "api/v1/category", categoryDto,{
      headers:this.createAuthorizationHeader()
    })
  }
    createAuthorizationHeader():HttpHeaders{
      let authHeaders:HttpHeaders = new HttpHeaders();
      return authHeaders.set(
        "Authorization","Bearer" + StorageService.getToken()
      );

    }
}
