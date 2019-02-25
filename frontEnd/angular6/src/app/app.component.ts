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

  listTaskFilter: any = [];
  listTask;
  private _listFilter: string;
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.listTaskFilter = this.listFilter ? this.perFormFilter(this.listFilter) : this.listTask;
  }

  constructor(private autService: AuthenticationService, private  router: Router) {
    this.listTask = this.autService.getTasks();
    this.listTaskFilter = this.listTask;
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

  logOut() {
    this.autService.logout();

  }


  postTask() {
    this.router.navigateByUrl("/new-task");
  }

  private perFormFilter(filterby: string) {
    filterby = filterby.toLocaleLowerCase();
    return this.listTask.filter(task => {
      task.message.toLocaleLowerCase().indexOf(filterby);
    });
  }
}
