import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserCreateComponent } from './user-create/user-create.component';

import { QuestionairesCreateComponent } from './questionaires-create/questionaires-create.component';
import { QuestionairesParticipateComponent } from './questionaires-participate/questionaires-participate.component';
import { QuestionairesSavedComponent } from './questionaires-saved/questionaires-saved.component';
import { UserService } from './service/user.service';

import { SurveySearchComponent } from './survey-search/survey-search.component';

import { SurveySearchResultComponent } from './survey-search-result/survey-search-result.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ChangeBgDirective } from './change-bg.directive';

import { SurveyResultResultComponent } from './survey-result-result/survey-result-result.component';
import { CookieService } from 'ngx-cookie-service';
import { FileService } from './service/file.service';
import { TokenInterceptorService } from './service/token-interceptor.service';

const appRoutes : Routes = [
  {path: '', component: UserLoginComponent},
  {path: 'usercreate', component: UserCreateComponent},
  {path: 'myquest/:userId', component: QuestionairesSavedComponent},
  {path: 'questcreate/:userId', component: QuestionairesCreateComponent},
  {path: 'questsearch/result', component: SurveySearchResultComponent},
  {path: 'questsearch/:userId', component: SurveySearchComponent},
  {path: 'questparticipate/:userId', component: QuestionairesParticipateComponent},
  {path: 'questresults/:userId', component: SurveyResultResultComponent},
  {path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    UserCreateComponent,
    QuestionairesCreateComponent,
    QuestionairesParticipateComponent,
    QuestionairesSavedComponent,
    SurveySearchComponent,
    SurveySearchResultComponent,
    ChangeBgDirective,
    SurveyResultResultComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, MaterialModule, HttpClientModule, ReactiveFormsModule, FormsModule, 
    RouterModule.forRoot(appRoutes, {useHash: true}), FontAwesomeModule
  ],
  providers: [UserService, FileService, CookieService, 
    {provide: HTTP_INTERCEPTORS, 
    useClass: TokenInterceptorService, 
    multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
