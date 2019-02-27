import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  tasks;
  listTaskFilter: any = [];
  listTask;
  private _listFilter: string;
  private currentUrl: string;
  edit: boolean = false;

  get listFilter(): string {
    return this._listFilter;
  }

  // set listFilter(value: string) {
  //   value = this.authService.getFilter();
  //   this._listFilter =  value;
  //   this.listTaskFilter = this._listFilter ? this.perFormFilter(this.listFilter) : this.listTask;
  // }

  constructor(private authService: AuthenticationService, private router: Router) {
    this.router.events.subscribe((_: NavigationEnd) => this.currentUrl = _.url);
    this.listTaskFilter = this._listFilter ? this.perFormFilter(this.listFilter) : this.listTask;
  }

  ngOnInit() {
    this._listFilter = this.authService.getFilter();
    this.getTasks();
  }

  getTasks() {
    this.authService.getTasks().subscribe(resp => {
      this.tasks = resp;
      this.listTask = this.tasks;
      this.listTaskFilter = this.listTask;
    }, error1 => {
      this.authService.logout();
      this.router.navigateByUrl("/login");
    })
  }

  private perFormFilter(filterby: string) {
    console.log(filterby, "task");
    filterby = filterby.toLocaleLowerCase();
    return this.listTask.filter(task => {
      task.message.toLocaleLowerCase().indexOf(filterby);
    });
  }

  onDeleteTask(id) {
    let c = confirm("Etes vous sure de supprimer !!")
    if (!c) return;
    console.log(id);
    this.authService.deleteTask(id).subscribe(resp => {
      this.getTasks();
    }, error1 => {
      console.log(error1);
    })
  }

  onEditTask(id) {
    this.edit = true;
    this.authService.editTask(id).subscribe(resp => {
      console.log(resp);
    }, error => {
      console.log(error);
    })
  }
}
