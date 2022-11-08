import { Component, OnInit } from '@angular/core';
import { FileService } from '../service/file.service';



@Component({
  selector: 'app-survey-result-result',
  templateUrl: './survey-result-result.component.html',
  styleUrls: ['./survey-result-result.component.css']
})
export class SurveyResultResultComponent implements OnInit {

  urlMap = new Map<string, string>()

  surveyId !: string

  displayGraphs : boolean = false

  constructor(private fileSvc : FileService) { }

  ngOnInit(): void {
    this.fileSvc.getResults(this.fileSvc.surveyId).subscribe(x => {
      this.urlMap = x;
      console.log(this.urlMap)});
    
  }

  display(){
    this.displayGraphs = true;
  }

  



}
