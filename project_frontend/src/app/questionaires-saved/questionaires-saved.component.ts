import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

import { Survey } from '../model';
import { FileService } from '../service/file.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-questionaires-saved',
  templateUrl: './questionaires-saved.component.html',
  styleUrls: ['./questionaires-saved.component.css']
})
export class QuestionairesSavedComponent implements OnInit, OnDestroy {

  retrievedSurvey : Survey[] = []
  subscription !: Subscription
  surveyId !: string

  constructor(private fileSvc: FileService, private userSvc: UserService) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
   this.subscription = this.fileSvc.getSurveyByUser(this.userSvc.sessId).subscribe(x => this.retrievedSurvey = x);
  }

  deleteSurvey(i : number){
    this.surveyId = this.retrievedSurvey.at(i)?.surveyId!
    this.fileSvc.deleteSurvey(this.surveyId).subscribe(x => {
      alert ("Survey is deleted")
      this.ngOnInit();
    }, error => {
      alert ("Error in deleting survey")
    })
  }

}
