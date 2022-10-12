import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class UploadfileService {

  constructor(private http: HttpClient) { }

  pushFileToBackend (file : File) {
   const data : FormData = new FormData();
   data.set('file', file);
   
   
   const newRequest = new HttpRequest('POST', '/createquiz', data, {reportProgress: true,
    responseType: 'text'});
    return this.http.request(newRequest);
  }

  upload( file: File, sessId : string ) {
    const data = new FormData()
    
    data.set('myfile', file)
    return firstValueFrom(
      this.http.post<any>(`/createquiz/${sessId}`, data)
    )
  }


}
