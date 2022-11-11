import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { AnsweredSurvey, Attempt, Question } from '../model';
import { FileService } from '../service/file.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-questionaires-participate',
  templateUrl: './questionaires-participate.component.html',
  styleUrls: ['./questionaires-participate.component.css']
})
export class QuestionairesParticipateComponent implements OnInit {
  progress : string = "0";
  email !: string
  subscription !: Subscription
  questionList : Question[] = []
  currentQuestion : number = 0;
  currentIndex !: number

  questionId !: string
  answerId !: string
  sessId !: string
  surveyId !: string

  responses : AnsweredSurvey = {
    questionId :'' ,
    answerId :''
  }
  

  isQuizCompleted : boolean = false;

  constructor(private userSvc : UserService, private fileSvc : FileService, private router : Router) { }

  ngOnInit(): void {
    this.email = this.userSvc.sessId;
    this.subscription = this.fileSvc.loadSurvey(this.fileSvc.surveyId).subscribe(x => {
      this.questionList = x;
      this.getProgressPercent();

    });
  }
  

   answer(questionNo : number, optionId : any){
    
    this.questionId = this.questionList[questionNo].questionId;
    this.answerId = optionId;

    this.responses.questionId = this.questionId
    this.responses.answerId = this.answerId
    
    this.fileSvc.answeredObject.push({...this.responses})
    
    this.sessId = this.userSvc.sessId;

    this.surveyId = this.fileSvc.surveyId;


    this.fileSvc.attemptSubmit.surveyId = this.fileSvc.surveyId;
    this.fileSvc.attemptSubmit.userId = this.userSvc.sessId;
   
    
    if ((questionNo + 1) == this.questionList.length){
      this.isQuizCompleted = true;
    }
  setTimeout(() => {
    this.nextQuestion();
  }, 500)
    
  }

  submitAttempt(){
    this.fileSvc.attemptSubmit.answeredSurveys = this.fileSvc.answeredObject;
    this.fileSvc.createAttempt(this.fileSvc.attemptSubmit, this.userSvc.sessId).subscribe(data => {
      alert ("Attempt submitted")
      this.router.navigate(['/questresults', this.userSvc.sessId]);
    }, error => {
      alert ("Attempt not submitted")
    })
  }

  nextQuestion(){
    if(this.currentQuestion +1  < this.questionList.length){
      this.currentQuestion++;
    }
    this.getProgressPercent();
  }

  getProgressPercent(){
    this.currentIndex = this.currentQuestion + 1;
    this.progress = ((this.currentIndex/this.questionList.length)*100).toString();
    
    return this.progress;
  }

  

}
