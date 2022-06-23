import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Resume } from '../models/resume.model';
import { ResumeService } from '../services/resume.service';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss']
})
export class ResumeComponent implements OnInit {
  resumeForm!:FormGroup;
  resume = new Resume(1,"","");
  public resumes!: Resume[];
  public editResume!: Resume;
  public deleteResume!: Resume;
  errorMessage = '';
  constructor(private resumeService:ResumeService) { }

  ngOnInit(): void {
    this.resumeForm = new FormGroup({
      headline:new FormControl(null,[Validators.required,Validators.minLength(3)]),
      path: new FormControl(null,[Validators.required,Validators.minLength(3)])
    })
    console.log("I am here")
  }

  onSubmit(){
    console.log("I am here in submit")
      this.resume.path = this.resumeForm.value.path;
      this.resume.headline = this.resumeForm.value.headline;
      console.log(this.resumeForm);
      this.addResume();

  }

  addResume(){
          this.resumeService.addResume(this.resume).subscribe({
        next:(resume:Resume)=>{
          console.log(resume);
        },
        error:(err:HttpErrorResponse)=>{
          this.errorMessage = err.message;
        }
      })
  }
}
