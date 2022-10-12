import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserCreateComponent } from './user-create/user-create.component';

import { QuestionairesCreateComponent } from './questionaires-create/questionaires-create.component';
import { QuestionairesParticipateComponent } from './questionaires-participate/questionaires-participate.component';
import { QuestionairesSavedComponent } from './questionaires-saved/questionaires-saved.component';
import { UserService } from './user.service';
import { NgxCsvParserModule } from 'ngx-csv-parser';

const appRoutes : Routes = [
  {path: '', component: UserLoginComponent},
  {path: 'usercreate', component: UserCreateComponent},
  {path: 'myquest/:userId', component: QuestionairesSavedComponent},
  {path: 'questcreate/:userId', component: QuestionairesCreateComponent},
  {path: 'questparticipate/:userId', component: QuestionairesParticipateComponent},
  {path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserCreateComponent,
    QuestionairesCreateComponent,
    QuestionairesParticipateComponent,
    QuestionairesSavedComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, MaterialModule, HttpClientModule, ReactiveFormsModule, FormsModule, 
    RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
