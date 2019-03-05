import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit, OnChanges {
  tasks;
  listTaskFilter: any = [];
  private _listFilter: string;
  private currentUrl: string;
  edit: boolean = false;
  tasksCurrent;
  @Input() private filter: string;

  constructor(private authService: AuthenticationService, private router: Router) {
    this.router.events.subscribe((_: NavigationEnd) => this.currentUrl = _.url);
    this.listTaskFilter = this._listFilter ? this.perFormFilter(this.listFilter) : this.tasks;

  }
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter   = value ;
    this.listTaskFilter = this._listFilter ? this.perFormFilter(this.listFilter) : this.tasks;
  }

  ngOnInit() {
    this.getTasks();
    this.listTaskFilter = this._listFilter ? this.perFormFilter(this.filter) : this.tasks;
    console.log(this.filter);
  }

  getTasks() {
    this.authService.getTasks().subscribe(resp => {
      this.tasks = resp;
      this.listTaskFilter = this.tasks;
    }, error1 => {
      this.authService.logout();
      this.router.navigateByUrl("/login");
    })
  }

  private perFormFilter(filterby: string) {
    console.log(filterby, "task");
    filterby = filterby.toLocaleLowerCase();
    return this.tasks.filter(task =>
      task.message.toLocaleLowerCase().indexOf(filterby) != -1);
  };

  onDeleteTask(id) {
    let c = confirm("Êtes vous sure de supprimer !!")
    if (!c) return;
    console.log(id);
    this.authService.deleteTask(id).subscribe(resp => {
      this.getTasks();
    }, error1 => {
      console.log(error1);
    })
  }

  onEditTask(task) {
    this.edit = true;
    console.log(task);
    this.authService.editTask(task).subscribe(resp => {
      this.tasksCurrent = resp;
      this.getTasks();
      console.log(resp);
    }, error => {
      console.log(error);
    })
  }
  ngOnChanges(): void {
    console.log(this.filter +" task");
    this.listTaskFilter = this._listFilter ? this.perFormFilter(this.filter) : this.tasks;
  }

}
