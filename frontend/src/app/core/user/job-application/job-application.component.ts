import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobApplicationService } from '../services/job-application.service';
import { JobApplication } from '../models/job-application.model';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.scss']
})
export class JobApplicationComponent implements OnInit {
  jobApplicationForm!: FormGroup;
  jobListingsId!:number;
  successfulApplication: boolean = false;
  jobApplication = new JobApplication(1,new Date());
  constructor(private jobApplicationService:JobApplicationService, private activatedRoute:ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.jobListingsId = this.activatedRoute.snapshot.params['id'];
    console.log(this.jobListingsId);
    this.jobApplicationForm = new FormGroup({
      applicationDate:new FormControl(null,[Validators.required,Validators.minLength(3)]),
    })
  }

  onSubmit(){
    this.jobApplication.applicationDate = this.jobApplicationForm.value.applicationDate;
    this.apply();
  }
  public apply(){
    this.jobApplicationService.apply(this.jobApplication,this.jobListingsId)
                              .subscribe({
                                next:(response:JobApplication)=>{
                                  console.log(JobApplication)
                                  this.successfulApplication = true;
                                  setTimeout(() => {
                                    this.router.navigate(['/user/jobs']);
                                }, 5000);  //5s
                                },
                                error:(err:HttpErrorResponse)=>{
                                  console.log(err.message);
                                }
                              });
  }

}
