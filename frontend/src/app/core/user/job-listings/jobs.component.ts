import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { JobListing } from '../../admin/models/job-listing.model';
import { JobListingsService } from '../../admin/services/job-listings.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-job-listings',
  templateUrl: './job-listings.component.html',
  styleUrls: ['./job-listings.component.scss']
})
export class JobsComponent implements OnInit {
  public isLoggedIn!: boolean;
  public jobListings!: JobListing[];
  constructor(private jobListingsService:JobListingsService,private authService:AuthService) { }

  ngOnInit(){
    this.getJobListings();
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  public getJobListings():void{
    this.jobListingsService.getJobListings().subscribe(
      (response: JobListing[])=>{
        this.jobListings = response;
      },(error: HttpErrorResponse) =>{
        alert(error.message);
      }
    )
  }


}
