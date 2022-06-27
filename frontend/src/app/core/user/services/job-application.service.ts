import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JobApplication } from '../models/job-application.model';
@Injectable({
  providedIn: 'root'
})
export class JobApplicationService {
  private apiURL = environment.baseApiUrl + 'user';
  constructor(private http:HttpClient) { }

  public apply(jobApplication:JobApplication,JobListingId:number):Observable<JobApplication>{
    return this.http.post<JobApplication>(`${this.apiURL}/${JobListingId}/apply`,jobApplication,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } }
    );
  }
}
