import { Component, OnDestroy, OnInit } from '@angular/core';

import { Survey } from '../model';
import { UserService } from '../service/user.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { FileService } from '../service/file.service';

@Component({
  selector: 'app-survey-search-result',
  templateUrl: './survey-search-result.component.html',
  styleUrls: ['./survey-search-result.component.css']
})
export class SurveySearchResultComponent implements OnInit, OnDestroy {

  subscription !: Subscription
  retrievedSurvey : Survey[] = []
  email !: string 
  surveyId !: string 


  constructor(private fileSvc: FileService, private userSvc: UserService, private router: Router) { }

  ngOnInit(): void {
    this.subscription = this.fileSvc.getSurveyByEmail(this.fileSvc.email).subscribe(x => this.retrievedSurvey = x);
    this.email = this.fileSvc.email;

  }

  attempt(i : number){
    this.surveyId = this.retrievedSurvey.at(i)!.surveyId;
    console.log(">>> surveyId selected is ", this.surveyId);
    this.fileSvc.surveyId = this.surveyId;
    this.fileSvc.loadSurvey(this.surveyId).subscribe(data => {
      console.log(">>> loaded survey:", data);
      alert ("Survey successfully loaded")
      this.router.navigate(['/questparticipate', this.userSvc.sessId]);
    }, error => {
      console.log (">>> error : ", error);
      alert( "Survey is not loaded");
    })
  }


 ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }



}
