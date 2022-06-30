import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  private baseUrl = environment.adminApiUrl+ "users/";
  constructor(private http:HttpClient) { }

  public getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.baseUrl}`,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } })
  }

  public deleteUser(id:number):Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}
