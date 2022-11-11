import { HttpClient, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { AnsweredSurvey, Attempt, Question, Survey } from '../model';



@Injectable({
  providedIn: 'root'
})
export class FileService {

  email !: string
  surveyId !: string


  attemptSubmit : Attempt = {}
  answeredObject : AnsweredSurvey[] = [] 

  constructor(private http: HttpClient) { }


  upload(file: File, sessId: string, title: string) {
    const data = new FormData()

    data.set('myfile', file)
    data.set('title', title)
    return firstValueFrom(
      this.http.post<any>(`/createquiz/${sessId}`, data)
    )
  }

  getSurveyByUser(sessId: string): Observable<Survey[]> {
    return this.http.get<Survey[]>(`/getquizz/${sessId}`);
  }

  getSurveyByEmail (email : string): Observable<Survey[]>{
    let params1 = new HttpParams()
      .set("email", email)

    return this.http.get<Survey[]>('/searchuserquizzes', {params: params1});
  }

  loadSurvey(surveyId : string) : Observable<Question[]>{
    let params1  = new HttpParams()
      .set("surveyId", surveyId)
    
    return this.http.get<Question[]>('/displayquiz', {params: params1})
  }

  createAttempt(attempt : Attempt, sessId : string): Observable<Attempt>{
    const headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json')

    return this.http.post<Attempt>(`/createattempt/${sessId}`, attempt, {headers: headers});
  }

  getResults(surveyId : string) : Observable<Map<string, string>> {
    return this.http.get<Map<string, string>>(`/result/${surveyId}`);
  }

  deleteSurvey(surveyId : string): Observable<any>{
    const headers = new HttpHeaders()
    .set('Content-type', 'application/json')
    .set('Accept', 'application/json');
    let params = new HttpParams()
      .set("surveyId", surveyId);

    return this.http.delete<any>('/deletequiz', {headers: headers, params: params});
  }




}
