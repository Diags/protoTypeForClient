import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private currentUrl: string;

  constructor(private autService: AuthenticationService, private  router: Router) {
    this.router.events.subscribe((_:NavigationEnd) => this.currentUrl = _.url)
  }

@Output()  filterList: EventEmitter<string> = new EventEmitter<string>();

  ngOnInit() {
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

  logOut() {
    this.autService.logout();

  }


  postTask() {
    this.router.navigateByUrl("/new-task");
  }

  search(value) {
    this.filterList.emit(value);
    console.log(value,"header");
  }
}
