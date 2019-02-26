import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user:any;
  mode:number=0;
  errorMessage:string;
  private currentUrl: string;
  constructor(private authService:AuthenticationService,private router:Router) {
    this.router.events.subscribe((_:NavigationEnd) => this.currentUrl = _.url)
  }
  ngOnInit() {
  }
  onRegister(user){
    this.authService.register(user)
      .subscribe(data=>{
          this.user=data;
          this.mode=1;
          console.log(data);
        },
        err=>{
          this.errorMessage=err.error.message;
          this.mode=0;
        })
  }

}
