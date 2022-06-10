import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';


const headers = new HttpHeaders().set('Content-Type','application/json').set('Access-Control-Allow-Origin', 'http://localhost:8080/api/auth/signup');


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseApiUrl + "auth/";

  constructor(private http:HttpClient, private router:Router) {}

  signup(user: User): Observable<any>{
    console.log("in AuthService - sign up" + this.baseUrl+"signup");
    return this.http.post(this.baseUrl+'signup',user,{ headers, responseType: 'text'})
      .pipe(catchError(this.handleError));
    }

    login(user: string, password: string){
      // console.log('In AuthService -  login');
       return this.http.post<any>(this.baseUrl + 'login',
           {username: user, password:password}, {headers})
           .pipe(catchError(this.handleError),
               map(userData => {
                 sessionStorage.setItem("username", user);
                 let tokenStr = "Bearer " + userData.token;
                 console.log("Token---  " + tokenStr);
                 sessionStorage.setItem("token", tokenStr);
                 sessionStorage.setItem("roles", JSON.stringify(userData.roles));
                 console.log(userData);
                 return userData;
               })
           );
   }


   logout(){
     sessionStorage.clear();
     this.router.navigate(['/login']);
   }

   isLoggedIn():boolean {
     return sessionStorage.getItem('username') !== null;
   }

   private handleError(httpError: HttpErrorResponse){
     let message : string = '';

     if(httpError.error instanceof ProgressEvent){
       console.log('in progress event');
       message = "Network Error";
     }
     else{
       message = httpError.error.message;
        // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${httpError.status}, ` +
        `body was: ${httpError.error}`);
    }
    // Return an observable with a user-facing error message.
      return throwError(message);
     }
   }



