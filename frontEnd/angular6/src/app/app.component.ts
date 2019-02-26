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


  onFilter($event: string) {
    this.filter = $event;
      this.autService.onFilter($event);
    console.log($event, "je suis parent")
  }
}
