import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {
  private host: string = "http://localhost:8080";
  private name: string;
  private jwtToken: string;
  roles: Array<any>;
  filter: string;

  constructor(private http: HttpClient, private router: Router) {

  }

  login(user) {
    return this.http.post(this.host + "/signin", user, {observe: 'response'})
  }

  saveToken(jwtToken: string) {
    localStorage.setItem("token", jwtToken);
    this.jwtToken = jwtToken;
    console.log("admin ==> " + this.jwtToken);
    this.parsJWT();

  }

  parsJWT() {
    let jwtHelper = new JwtHelperService();
    let decodeToken = jwtHelper.decodeToken(this.jwtToken);
    this.name = decodeToken.sub;
    this.roles = decodeToken.roles;

    console.log("Roless ==>" + this.roles.map(p => p.authority).find(p => {
      return p == "ADMINISTRATOR"
    }));
  }

  isAdmin() {
    return this.roles.map(p => p.authority).find(p => {
      return p == "ADMINISTRATOR"
    });
  }

  isUser() {
    return this.roles.map(p => p.authority).find(p => {
      return p == "USER"
    });
  }

  loadToken() {
    this.jwtToken = localStorage.getItem("token");
    this.parsJWT();
  }

  isAuthentificated() {
    return this.roles;
  }

  getTasks() {
    if (this.jwtToken == null) this.loadToken();
    return this.http.get(this.host + "/tasks", {headers: new HttpHeaders({'authorization': this.jwtToken})})
  }

  logout() {
    localStorage.removeItem("token");
    this.initParam();
    this.router.navigateByUrl("/login");
  }

  initParam() {
    this.name = undefined;
    this.roles = undefined;
    this.jwtToken = undefined;
  }

  register(user: any) {
    return this.http.post(this.host + "/register", user);
  }

  postTask(task) {
    console.log(task);
    return this.http.post(this.host + "/savetasks", task, {headers: new HttpHeaders({'authorization': this.jwtToken})});
  }

  getTaskById(id) {
    return this.http.get(this.host + "/tasks/" + id, {headers: new HttpHeaders({'authorization': this.jwtToken})});
  }

  onFilter(val) {
    this.filter = val;
    console.log("service", val);
  }
  getFilter(){
    console.log("caco", this.filter);
    return this.filter;
  }

  deleteTask(id: any) {
    return this.http.delete(this.host+"/deletetask/"+id, {headers: new HttpHeaders({'authorization':'Bearer '+ this.jwtToken})});
  }

  editTask(id: any) {
    return this.http.put(this.host+"/updatetask/"+id, {headers: new HttpHeaders({'authorization':'Bearer '+ this.jwtToken})});
  }
}
