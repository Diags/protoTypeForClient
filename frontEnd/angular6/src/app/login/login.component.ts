import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  mode: number = 0;

  constructor(private http: HttpClient, private serviceAuth: AuthenticationService) {
  }

  ngOnInit() {
  }

  onLogin(dataForm) {
    this.serviceAuth.login(dataForm).subscribe(resp => {
      let jwtToken = resp.headers.get('Authorization');
      this.serviceAuth.saveToken(jwtToken);
    }, error => {
      this.mode = 1;
    console.log(dataForm)});
  }
}
