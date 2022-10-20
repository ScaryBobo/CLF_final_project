import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileService } from '../file.service';
import { Questionnaire } from '../model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-questionaires-saved',
  templateUrl: './questionaires-saved.component.html',
  styleUrls: ['./questionaires-saved.component.css']
})
export class QuestionairesSavedComponent implements OnInit {

  retrievedQuestionnaires !: Questionnaire[]

  constructor(private fileSvc : FileService, private userSvc : UserService) { }

  ngOnInit(): void {
    this.fileSvc.getSurveyByUser(this.userSvc.sessId).subscribe(x => this.retrievedQuestionnaires = x);
  }

}
