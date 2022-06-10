import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let modifiedReq = req;
    if(sessionStorage.getItem('username') && sessionStorage.getItem('token')){
      console.log("with Token --" + sessionStorage.getItem('token'))
      modifiedReq = req.clone({
        headers: req.headers.set('Authorization', sessionStorage.getItem('token')!)
      });
    }
    console.log(modifiedReq);
    return next.handle(modifiedReq);
  }

}
