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
import { HeaderComponent } from './header/header.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import {TaskDetailGuard} from "./task-detail.guard";

const appRoute:Routes = [
  {path:"login", component:LoginComponent},
  {path:"tasks",component:TaskComponent},
  {path:"new-task", component:NewTaskComponent},
  {path:"register",component:RegistrationComponent},
  {path:"task-details/:id", canActivate: [TaskDetailGuard], component: TaskDetailComponent},
  {path:"", redirectTo:"/tasks",pathMatch:"full"}
  ]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TaskComponent,
    NewTaskComponent,
    RegistrationComponent,
    HeaderComponent,
    TaskDetailComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoute), FormsModule, HttpClientModule
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
