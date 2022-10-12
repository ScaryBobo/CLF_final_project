import { HttpClient, HttpEventType, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { finalize, Subscription } from 'rxjs';
import * as XLSX from 'xlsx';
import { UploadfileService } from '../uploadfile.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-questionaires-create',
  templateUrl: './questionaires-create.component.html',
  styleUrls: ['./questionaires-create.component.css']
})
export class QuestionairesCreateComponent implements OnInit {

  
constructor(private http: HttpClient, private uploadSvc : UploadfileService, private userSvc: UserService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({

      file: this.fb.control<any>('', [ Validators.required ])
    })
  }

  @ViewChild('toUpload')
  toUpload!: ElementRef

  form!: FormGroup

  doUpload() {
    console.info('>>> upload: ', this.form.value)
    // @ts-ignore
    console.info('>>> toUpload: ', this.toUpload.nativeElement.files[0])

    const myFile = this.toUpload.nativeElement.files[0]
    
    this.uploadSvc.upload(myFile, this.userSvc.sessId )
      .then(result => {
        console.info('>>> result: ', result)
      }) .catch(error => {
        console.error('>> error: ', error)
      })
  }









}
