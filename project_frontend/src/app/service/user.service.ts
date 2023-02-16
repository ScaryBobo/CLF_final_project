import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { User } from '../model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  sessId !: string

  // Using promises
  addNewUserToDatabase (user : User){
    
    const headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json')

    return firstValueFrom(
      this.http.post<any> ('/createuser', user, {headers})
    )
  }

  //Using Observables
  addUser(user: User): Observable<any> {
    const headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json')
    return this.http.post('/createuser', user, {headers});
  }

  authenticate (user: User) : Promise<any> {
    const headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json')

    return firstValueFrom(this.http.post('/authenticate', user, {headers}));
  }
}
