import { BrowserModule } from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { TaskComponent } from './task/task.component';
import { NewTaskComponent } from './new-task/new-task.component';
import { RegistrationComponent } from './registration/registration.component';
import {Route, RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../services/authentication.service";
import { HeaderComponent } from './header/header.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';
import {TaskDetailGuard} from "./task-detail.guard";
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {JwtInterceptor} from './jwt.interceptor';

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
    TaskDetailComponent,
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoute), FormsModule, HttpClientModule, MDBBootstrapModule.forRoot()
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  providers: [AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
