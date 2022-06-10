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
  }

  onSubmit(){
    this.submitted = true;
    this.authService.login(this.loginForm?.value.username,this.loginForm?.value.password).subscribe(
      data => {
        this.isLoggedIn = true;
        this.router.navigate(['']);
      },
      error => {
        console.log(error);
        this.errorMessage = error;
        this.isLoggedIn = false;
        this.isLoginFailed = true;
      }
    )
  }

}
