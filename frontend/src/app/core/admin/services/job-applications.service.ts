import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JobApplications } from '../models/job-applications.model';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationsService {

  private apiUrl = environment.baseApiUrl + "admin/";
  constructor(private http : HttpClient) { }

  public getJobApplications(jobListingsId:number): Observable<JobApplications[]>{
 return this.http.get<JobApplications[]>(`${this.apiUrl}${jobListingsId}`,
 {headers:
  {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } }
  );
  }

}
