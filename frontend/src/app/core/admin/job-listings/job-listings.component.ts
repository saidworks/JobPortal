import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { JobListing } from '../models/job-listing.model';
import { JobListingsService } from '../services/job-listings.service';


@Component({
  selector: 'app-job-listings',
  templateUrl: './job-listings.component.html',
  styleUrls: ['./job-listings.component.scss']
})
export class JobListingsComponent implements OnInit {
  public jobListings!: JobListing[];
  public editJobListing!: JobListing ;
  public deleteJobListing!: JobListing;
  public isLoggedIn!: boolean;

  constructor(private jobListingsService:JobListingsService, private authService:AuthService ) { }

  ngOnInit(){
    this.getJobListings();
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  public getJobListings():void{
    this.jobListingsService.getJobListings().subscribe(
      (response: JobListing[])=>{
        this.jobListings = response;
      },(error: HttpErrorResponse) =>{
        console.log(error.message);
      }
    )
  }
  public onAddJobListings(addForm: NgForm): void {
    document.getElementById('add-offer-form')!.click();
    this.jobListingsService.addJobListings(addForm.value).subscribe(
      (response: JobListing) => {
        console.log(response);
        this.getJobListings();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        addForm.reset();
      }
    );
    console.log('test')
  }

  public onUpdateJobListings(jl: JobListing): void {
    this.jobListingsService.updateJobListings(jl).subscribe(
      (response: JobListing) => {
        console.log(response);
        this.getJobListings();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public onDeleteJobListings(JobListingId: number): void {
    this.jobListingsService.deleteJobListings(JobListingId).subscribe(
      (response: void) => {
        console.log(response);
        this.getJobListings();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public searchJobListings(key: string): void {
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


  public onOpenModal(jL: JobListing, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addJobListingModal');
    }
    if (mode === 'edit') {
      this.editJobListing = jL;
      button.setAttribute('data-target', '#updateJobListingsModal');
    }
    if (mode === 'delete') {
      this.deleteJobListing = jL;
      button.setAttribute('data-target', '#deleteJobListingsModal');
    }
    container?.appendChild(button);
    button.click();
  }


}
