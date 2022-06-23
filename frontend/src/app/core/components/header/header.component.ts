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
    this.getJobListings();
    this.isLoggedIn = this.authService.isLoggedIn();
  }


  isAdmin(){
    let roles:string[] = JSON.parse(sessionStorage.getItem("roles")!);
    if(roles?.length != null && this.isLoggedIn){
      for(let role of roles){
        if(role=="ROLE_ADMIN"){
          return true;
        }
      }
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

  public getJobListings():void{
    this.jobListingsService.getJobListings().subscribe(
      (response: JobListing[])=>{
        this.jobListings = response;
        console.log(this.jobListings);
      },(error: HttpErrorResponse) =>{
        alert(error.message);
      }
    )
  }



  public searchEmployees(key: string): void {
    console.log(key);
    const results: JobListing[] = [];
    for (const j of this.jobListings) {
      if (j.title.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || j.description.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || j.location.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || j.company.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(j);
      }
    }
    this.jobListings = results;
    if (results.length === 0 || !key) {
      this.getJobListings();
    }
  }



}
