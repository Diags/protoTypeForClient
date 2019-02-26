import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "selenium-webdriver";

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  private task: any;

  constructor(private authService:AuthenticationService, private routerActivated:ActivatedRoute, private router :Router ) {
    // this.router.params.subscribe(params => this.task = params.id)
    // console.log(this.router.snapshot.paramMap.get('id'))
  }

  ngOnInit() {
    let id = +this.routerActivated.snapshot.paramMap.get('id');
    this.authService.getTaskById(id).subscribe(resp => {
      this.task = resp;
    }, error =>{
      console.log(error);
    })
  }

  toTask() {
    this.router.navigateByUrl("/tasks");
  }
}
