import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private cookieSvc: CookieService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token = this.cookieSvc.get("userToken");
    console.log(">>> token is", token)

    let jwtToken = req.clone({
      headers: req.headers.set('Authorization','Bearer ' + token)
    })
    return next.handle(jwtToken);
  }
}
