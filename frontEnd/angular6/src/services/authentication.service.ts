import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AuthenticationService {
  private host:string = "htpp://localhost:8080";
  constructor(private http: HttpClient) {

  }

  login(user) {
    return this.http.post(this.host+"/signin",user, {observe:'response'})
  }

  saveToken(jwtToken: string) {

  }
}
