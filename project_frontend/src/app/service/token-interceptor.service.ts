import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {
  
  constructor(private cookieSvc: CookieService, private userSvc: UserService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token = this.cookieSvc.get(this.userSvc.sessId);

    let jwtToken = req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + token)
    })
    return next.handle(jwtToken);
  }
}
