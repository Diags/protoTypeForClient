import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  private tasks;

  constructor(private authService: AuthenticationService,private router :Router) {
  }

  ngOnInit() {
    this.authService.getTasks().subscribe(resp => {
      this.tasks = resp;
    },error1 => {
      this.authService.logout();
      this.router.navigateByUrl("/login");
    })
  }

}
