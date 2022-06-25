import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address.model';
@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private apiUrl = environment.baseApiUrl + "addresss/"

  constructor(private http:HttpClient) { }

  public getAddresses():Observable<Address[]>{
    return this.http.get<Address[]>(`${this.apiUrl}`,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } }
    )
  }

  public getAddress(): Observable<Address> {
    return this.http.get<Address>(`${this.apiUrl}myaddress`,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } });
  }

  public addAddress(address:Address):Observable<Address>{
    console.log("I am in service" + address);
    return this.http.post<Address>(`${this.apiUrl}`,address,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } });
  }
  public updateAddress(address:Address):Observable<Address>{
    return this.http.put<Address>(`${this.apiUrl}${address.id}`,address,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } });
  }

  public deleteAddress(addressId:number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}${addressId}`,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } });
  }

}
