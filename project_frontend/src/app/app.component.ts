import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  constructor(private userSvc: UserService, private router: Router){}

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

  participateQuest(){
    this.router.navigate(['/questparticipate', this.userSvc.sessId]);
  }



}
