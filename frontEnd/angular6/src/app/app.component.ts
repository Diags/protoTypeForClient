import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'angular6';
  filter: string;

  constructor(private autService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.autService.logout();
  }
  isAuthentificated() {
    return this.autService.isAuthentificated();
  }

  onFilter($event: string) {
    this.filter = $event;
    console.log(this.filter, "je suis parent")
  }
}
