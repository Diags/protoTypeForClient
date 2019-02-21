import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { TaskComponent } from './task/task.component';
import { NewTaskComponent } from './new-task/new-task.component';
import { RegistrationComponent } from './registration/registration.component';
import {Route, RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../services/authentication.service";

const appRoute:Routes = [
  {path:"login", component:LoginComponent},
  {path:"tasks",component:TaskComponent},
  {path:"new-task", component:NewTaskComponent},
  {path:"register",component:RegistrationComponent},
  {path:"",redirectTo:"/login",pathMatch:"full"}
  ]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TaskComponent,
    NewTaskComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoute), FormsModule, HttpClientModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
