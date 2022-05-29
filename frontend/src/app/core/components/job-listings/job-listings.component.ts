import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { JobListing } from '../../models/job-listing.model';
import { JobListingsService } from '../../services/job-listings.service';
@Component({
  selector: 'app-job-listings',
  templateUrl: './job-listings.component.html',
  styleUrls: ['./job-listings.component.scss']
})
export class JobListingsComponent implements OnInit {
  public jobListings!: JobListing[];
  public editJobListing!: JobListing ;
  public deleteJobListing!: JobListing;

  constructor(private jobListingsService:JobListingsService ) { }

  ngOnInit(){
    this.getJobListings();

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
  public onAddJobListings(addForm: NgForm): void {
    document.getElementById('add-employee-form')!.click();
    this.jobListingsService.addJobListings(addForm.value).subscribe(
      (response: JobListing) => {
        console.log(response);
        this.getJobListings();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
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




  public onOpenModal(jL: JobListing, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editJobListing = jL;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteJobListing = jL;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
  }


}
