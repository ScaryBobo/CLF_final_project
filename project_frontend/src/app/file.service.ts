import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { Questionnaire } from './model';
import { QuestionairesSavedComponent } from './questionaires-saved/questionaires-saved.component';


@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

 
  upload(file: File, sessId : string, title : string ) {
    const data = new FormData()
    
    data.set('myfile', file)
    data.set('title', title)
    return firstValueFrom(
      this.http.post<any>(`/createquiz/${sessId}`, data)
    )
  }

  getSurveyByUser(sessId: string) : Observable<Questionnaire[]>{
    console.log("getting all surveys by userId");
    return this.http.get <Questionnaire[]>(`/getquizz/${sessId}`);

  }
 



}
