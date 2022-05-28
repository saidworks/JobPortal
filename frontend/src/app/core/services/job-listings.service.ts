import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobListing } from '../models/job-listing.model';

@Injectable({
  providedIn: 'root'
})
export class JobListingsService {
  private apiUrl = environment.baseApiUrl;  
  constructor(private http : HttpClient) { }

  public getJobListings(): Observable<JobListing[]>{
    return this.http.get<JobListing[]>(`${this.apiUrl}/jobListings`);
  }
  
  public addJobListings(jobListing:JobListing): Observable<JobListing>{
    return this.http.post<JobListing>(`${this.apiUrl}/jobListings/`,jobListing);
  }
  
  public updateJobListings(jobListing:JobListing): Observable<JobListing>{
    return this.http.post<JobListing>(`${this.apiUrl}/jobListings/${jobListing.id}`,jobListing);
  }

  public deleteJobListings(jobListingId:number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/jobListings/${jobListingId}`);
  }
  

}
