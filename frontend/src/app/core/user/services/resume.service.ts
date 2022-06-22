import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Resume } from '../models/resume.model';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  private apiUrl = environment.baseApiUrl + "resumes/"
  constructor(private http: HttpClient) { }

  public getResumes():Observable<Resume[]>{
    return this.http.get<Resume[]>(`${this.apiUrl}`,
    {headers:
      {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' } }
    )
  }

  public getResume(resumeId:number): Observable<Resume> {
    return this.http.get<Resume>(`${this.apiUrl}${resumeId}`);
  }

  public addResume(resume:Resume):Observable<Resume>{
    return this.http.post<Resume>(`${this.apiUrl}`,resume);
  }
  public updateResume(resume:Resume):Observable<Resume>{
    return this.http.put<Resume>(`${this.apiUrl}${resume.id}`,resume);
  }

  public deleteResume(resumeId:number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}${resumeId}`);
  }

}

