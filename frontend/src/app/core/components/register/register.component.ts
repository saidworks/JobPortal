import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../models/user.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registrationForm!: FormGroup;
  user = new User('','','','','','','',[]);
  isRegistered = false;
  submitted = false;
  errorMessage = '';
  roles : any = [
    {roleName:"User", id:1, selected: true},
    {roleName:"Admin", id:2, selected: false},
  ]
  selectedRoles!: string[];
  constructor(private authService: AuthService) { }

  ngOnInit(){
    this.registrationForm = new FormGroup({
      username: new FormControl(null,[Validators.required,Validators.minLength(5), Validators.maxLength(20)]),
      firstName: new FormControl(null,[Validators.required,Validators.minLength(3)]),
      lastName: new FormControl(null,[Validators.required,Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, Validators.minLength(8)]),
      course: new FormControl(null,[Validators.required,Validators.minLength(5)]),
      campus: new FormControl(null,[Validators.required,Validators.minLength(5)]),
      roleSelection: this.createRoles(this.roles)
    });
  }

  //create roles from array
  createRoles(roleList:any):FormArray{
    const arr = roleList.map((role: any)=> {
      return new FormControl(role.selected)
    }) ;
    return new FormArray(arr);
  }

  onSubmit(){
    this.submitted = true;
    this.user.username = this.registrationForm.value.username;
    this.user.firstName = this.registrationForm.value.firstName;
    this.user.lastName = this.registrationForm.value.lastName;
    this.user.email = this.registrationForm.value.email;
    this.user.password = this.registrationForm.value.password;
    this.user.campus = this.registrationForm.value.campus;
    this.user.course = this.registrationForm.value.course;
    //console.log(this.getSelectedRoles());
    this.user.roles = this.getSelectedRoles();
    this.registerUser()
  }
  // testing new syntax
  registerUser(){
    this.authService.signup(this.user)
    .subscribe({
      next: (user:User) => {
        console.log(user);
        this.isRegistered = true;
      },
      error: (err:HttpErrorResponse)=>{
        this.errorMessage = err.error.message;
        this.isRegistered = false;
      }
    })
  }

  getSelectedRoles():string[]{
    this.selectedRoles = this.registrationForm.value.roleSelection
    .map((selected,i) => {
      if(selected){
        return this.roles[i].name;
      }
      else{
        return '';
      }
    })
    //return selected roles
    return this.selectedRoles.filter(function(element){
      if(element !== ''){
        return element;
      }
      return [];
    })
  }

}
