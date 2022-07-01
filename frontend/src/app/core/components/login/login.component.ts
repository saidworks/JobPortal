import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMessage = "";
  isLoggedIn = false;
  isLoginFailed = false;

  constructor(private authService: AuthService, private router:Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
        username: new FormControl(null,Validators.required),
        password: new FormControl(null,Validators.required)
    });
    if(this.isLoggedIn){
      this.router.navigate(["/user"]);
    }
  }

  onSubmit(){
    this.submitted = true;
    this.authService.login(this.loginForm?.value.username,this.loginForm?.value.password).subscribe(
      data => {
        this.isLoggedIn = true;
        this.profileSwitch();
      },
      error => {
        console.log(error);
        this.errorMessage = error;
        this.isLoggedIn = false;
        this.isLoginFailed = true;
      }
    )
  }

  profileSwitch(){
    let roles:Array<String> = JSON.parse(sessionStorage.getItem("roles")!);
    console.log(roles.includes("ROLE_ADMIN") && this.isLoggedIn)
    if(roles.includes("ROLE_ADMIN")){
      // for(let role of roles){
      //   if(role.match("ROLE_ADMIN")){
      //     console.log("Admin");
      //     this.router.navigate(['/admin']);
      //     break;
      //   }
      // }
           console.log("Admin");
          return this.router.navigate(['/admin']);
   }
   console.log("user");
   return this.router.navigate(['/user']);
  }

}
