import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { JobListing } from '../../admin/models/job-listing.model';
import { AuthService } from '../../services/auth.service';
import { JobListingsService } from '../../admin/services/job-listings.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public editJobListing!: JobListing;
  public deleteJobListing!: JobListing;
  public jobListings!: JobListing[];
  isLoggedIn = false;
  username: string = '';

  constructor(private jobListingsService:JobListingsService,private authService:AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }


  isAdmin(){
    let roles:string[] = JSON.parse(sessionStorage.getItem("roles")!);
    if(roles.includes("ROLE_ADMIN")){
          return true;
   }
    return false;
  }
  getUserName(){
     return sessionStorage.getItem("username");
  }
  onLogOut():void{
    this.authService.logout();
  }

  loggedIn(){
    return this.authService.isLoggedIn()
  }




}
