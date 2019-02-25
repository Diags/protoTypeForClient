import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular6';

  constructor(private autService: AuthenticationService, private  router:Router) {

  }

  ngOnInit(): void {
    this.autService.logout();
  }

  isAdmin() {
    return this.autService.isAdmin();
  }

  isUser() {
    return this.autService.isUser();
  }

  isAuthentificated() {
    return this.autService.isAuthentificated();
  }
  logOut(){
 this.autService.logout();

}


  postTask() {
    this.router.navigateByUrl("/new-task");
  }
}
