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
  hasResume:boolean = false;

  constructor(private resumeService:ResumeService) { }

  ngOnInit(): void {
    this.resumeForm = new FormGroup({
      headline:new FormControl(null,[Validators.required,Validators.minLength(3)]),
      path: new FormControl(null,[Validators.required,Validators.minLength(3)])
    })
    console.log("I am here");
    this.getResume();
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
          this.hasResume = true;
          window.location.reload();
        },
        error:(err:HttpErrorResponse)=>{
          this.errorMessage = err.message;
        }
      })
  }

  public getResume():void{

      this.resumeService.getResume().subscribe(
        (response: Resume)=>{
          this.resume = response;
          if(this.resume){
            this.hasResume = true;
          }
        },(error: HttpErrorResponse) =>{
          console.log(error.message);
        }
      )

  }

  public updateResume(resume:Resume):void{
    console.log(resume);
    this.resumeService.updateResume(resume).subscribe({
      next:(resume:Resume)=>{
        console.log(resume);
        window.location.reload();
      },
      error:(err:HttpErrorResponse) =>{
        console.log(err.message);
      }
    })
  }

  public removeResume(resume:Resume):void{
    this.resumeService.deleteResume(resume.id).subscribe({
      next:(response:void)=>{
        console.log("resume deleted")
      },
      error:(err:HttpErrorResponse)=>{
        console.log(err.message);
      }
    });
  }

  public onOpenModal(resume: Resume, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addResumeModal');
    }
    if (mode === 'edit') {
      console.log(resume);
      this.editResume = resume;
      button.setAttribute('data-target', '#updateResumeModal');
    }
    if (mode === 'delete') {
      this.deleteResume = resume;
      button.setAttribute('data-target', '#deleteResumeModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
