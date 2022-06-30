import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users!: User[];
  constructor(private userService:UserserviceService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe({
      next:(response:User[])=>{
        this.users = response;
      },
      error:(err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    });
  }

  deleteUser(id:number){
    this.userService.deleteUser(id).subscribe({
      next:(response:void)=>{
        this.getAllUsers();
      },
      error:(err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    });
  }

}
