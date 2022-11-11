import { HttpClient, HttpEventType, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { finalize, Subscription } from 'rxjs';
import * as XLSX from 'xlsx';
import { FileService } from '../service/file.service';

import { UserService } from '../service/user.service';

@Component({
  selector: 'app-questionaires-create',
  templateUrl: './questionaires-create.component.html',
  styleUrls: ['./questionaires-create.component.css']
})
export class QuestionairesCreateComponent implements OnInit {

  
constructor(private fileSvc : FileService, private userSvc: UserService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({

      file: this.fb.control<any>('', [ Validators.required ]),
      title: this.fb.control<string>('', [ Validators.required ])
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
    const title = this.form.get('title')?.value
    
    this.fileSvc.upload(myFile, this.userSvc.sessId, title )
      .then(result => {
        alert("File has uploaded successfully")
      }) .catch(error => {
        alert("File is not uploaded")
      })
  }









}
