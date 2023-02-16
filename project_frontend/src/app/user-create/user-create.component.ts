import { compileNgModule } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '../model';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  createUser !: FormGroup
  hide :boolean = true;
  email !: string

  constructor(private fb: FormBuilder, private snackBar: MatSnackBar, private router : Router, private userSvc: UserService) { }

  ngOnInit(): void {
    this.createUser = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

 onCreateUser(){
    let user : User = this.createUser.value as User;
    this.userSvc.addUser(user).subscribe(data => {
      alert ("Account is successfully created");
      this.router.navigate(['/']);
    }, error => {
      alert("Account already exists");
    });
  }

  openSnackBar (message : string, action : string) {
    let user : User = this.createUser.value as User;
    this.email = user.email;
    this.userSvc.addUser(user).subscribe(data => {
      message = "User: " + this.email + " has been created";
    this.snackBar.open(message, action, {duration: 1500});
      
    }, error => {
      message = "User: " + this.email + " already exists";
      this.snackBar.open(message, action, {duration: 1500});
    });
  }
}
