import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  mode: number = 0;

  constructor(private serviceAuth: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
  }

  onLogin(dataForm) {
    this.serviceAuth.login(dataForm).subscribe(resp => {
      let jwtToken = resp.headers.get('Authorization');
      console.log(resp);
      console.log(jwtToken);
      this.serviceAuth.saveToken(jwtToken);
      this.router.navigateByUrl("/tasks");
    }, error => {
      this.mode = 1;
    });
  }

}
