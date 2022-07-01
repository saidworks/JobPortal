import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobApplications } from '../models/job-applications.model';
import { JobApplicationsService } from '../services/job-applications.service';

@Component({
  selector: 'app-job-applications',
  templateUrl: './job-applications.component.html',
  styleUrls: ['./job-applications.component.scss']
})
export class JobApplicationsComponent implements OnInit {
  jobListingsId!:number;
  jobApplications!: JobApplications[];
  count:number = 0 ;
  constructor(private jobApplicationsService:JobApplicationsService,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.jobListingsId = this.activatedRoute.snapshot.params['id'];
    this.getJobApplications();
  }
  getJobApplications(){
    this.jobApplicationsService.getJobApplications(this.jobListingsId).subscribe({
      next:(response:JobApplications[])=>{
        this.jobApplications = response},
      error:(err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    });
  }


}
