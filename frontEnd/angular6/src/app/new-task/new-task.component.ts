import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {
  user: any;
  mode: number = 0;
  errorMessage: string;
  task : any;
  constructor(private authService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
  }

  onRegister(user) {
    this.authService.register(user)
      .subscribe(data => {
          this.user = data;
          console.log(data);
          this.mode = 1;
        },
        err => {
          this.errorMessage = err.error.message;
          this.mode = 0;
        })
  }

  onPost(task) {
    this.authService.postTask(task)
      .subscribe(data => {
        this.task = data;
        console.log(data);

        setTimeout(()=>{    //<<<---    using ()=> syntax
          this.mode = 1;
      }, 50000);
        this.router.navigateByUrl("/tasks");
  },  err => {
        this.errorMessage = err.error.message;
        this.mode = 0;
      });
}
}
