import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { FileService } from '../file.service';
import { Survey } from '../model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-questionaires-saved',
  templateUrl: './questionaires-saved.component.html',
  styleUrls: ['./questionaires-saved.component.css']
})
export class QuestionairesSavedComponent implements OnInit, OnDestroy {

  retrievedSurvey : Survey[] = []
  subscription !: Subscription

  constructor(private fileSvc: FileService, private userSvc: UserService) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
   this.subscription = this.fileSvc.getSurveyByUser(this.userSvc.sessId).subscribe(x => this.retrievedSurvey = x);
  }

}
