import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginForm !: FormGroup

  hide: boolean = false;

  userInput !: User

  constructor(private fb: FormBuilder, private userSvc: UserService, private router : Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  onLogin(){
    console.log("form value >>>>>", this.loginForm.value);
    let user : User = this.loginForm.value as User;
    this.userSvc.authenticate (user).subscribe(data => {
      alert ("Login Successfully!");
      console.log("return>>>> : " , data);
      this.userSvc.sessId = data["userId"];
      console.log(">>> session id ",this.userSvc.sessId);
      this.router.navigate(['/myquest', this.userSvc.sessId]);
    }, error => {
      alert ("Email or password is incorrect");
    })
  }

  
}
