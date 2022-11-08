import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token 
    = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZXNib2JvOTNAZ21haWwuY29tIiwiZXhwIjoxNjY3OTM2MzcxLCJpYXQiOjE2Njc5MDAzNzF9.huuR0w0C--kXS7-zXi2QdQU87c0OO7N-lY2oSQ1ihuw';

    let jwtToken = req.clone({
      headers: req.headers.set('Authorization','Bearer ' + token)
    })
    return next.handle(jwtToken);
  }
}
