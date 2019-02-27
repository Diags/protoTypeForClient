import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskDetailGuard implements CanActivate {
  constructor(private  router :Router){

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let id = +next.url[1].path;
    if(isNaN(id) || id <1){
      this.router.navigateByUrl("/tasks");
      alert("nop !! this is id is not ok ");
      return false;
    }
    return true;
  }
}
