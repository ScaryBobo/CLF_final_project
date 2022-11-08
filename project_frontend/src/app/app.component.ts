import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './service/user.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  
 

  constructor(private userSvc: UserService, private router: Router, private cookies : CookieService){}

  login(){
    this.router.navigate(['/']);
  }

  createUser(){
    this.router.navigate(['/usercreate'])
  }

  myQuest(){
    
    this.router.navigate(['/myquest', this.userSvc.sessId]);
  }

  createQuest(){
    this.router.navigate(['/questcreate', this.userSvc.sessId]);
  }

  searchQuest(){

    this.router.navigate(['/questsearch', this.userSvc.sessId]);
  }

  async logout(){
    this.userSvc.sessId ='';
    sessionStorage.clear();
    localStorage.clear();
    await this.cookies.deleteAll();
    this.router.navigate(['/']);
  }



}
