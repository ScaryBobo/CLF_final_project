import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FileService } from '../file.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-survey-search',
  templateUrl: './survey-search.component.html',
  styleUrls: ['./survey-search.component.css']
})
export class SurveySearchComponent implements OnInit {

  searchSurveyForm !: FormGroup
  email !: string
  constructor(private fb: FormBuilder, private userSvc : UserService, private fileSvc : FileService, private router: Router) { }

  ngOnInit(): void {
    this.searchSurveyForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    })

  }

  search(){
    let email = this.searchSurveyForm.value.email;
    this.fileSvc.email = email;
    console.log(">>>> email: ", email);
    this.fileSvc.getSurveyByEmail(email).subscribe(data => {
      console.log("return>>>> : " , data);
      this.router.navigate(['/questsearch/result']);
    }, error => {
      alert ("No surveys found from this user"); 
    });

  }

}
