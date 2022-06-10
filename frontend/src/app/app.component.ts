import { Component } from '@angular/core';
import { AuthService } from './core/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private authService: AuthService){}
  // isLoggedIn = false;
  // username: string = '';
  ngOnInit(): void {
    // this.isLoggedIn = this.authService.isLoggedIn();
  }
  // getUserName(){
  //    return sessionStorage.getItem("username");
  // }
  // onLogOut(){
  //   this.authService.logout();
  // }

  // loggedIn(){
  //   return this.authService.isLoggedIn()
  // }
}
