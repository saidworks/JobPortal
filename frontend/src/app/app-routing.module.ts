import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobListingsComponent } from './core/admin/job-listings/job-listings.component';
import { LoginComponent } from './core/components/login/login.component';
import { RegisterComponent } from './core/components/register/register.component';
import { AddressComponent } from './core/user/address/address.component';
import { ResumeComponent } from './core/user/resume/resume.component';
import { JobsComponent } from './core/user/job-listings/jobs.component';
import { JobApplicationComponent } from './core/user/job-application/job-application.component';
import { DashboardComponent } from './core/user/dashboard/dashboard.component';
import { ADashboardComponent } from './core/admin/dashboard/dashboard.component';
import { JobApplicationsComponent } from './core/admin/job-applications/job-applications.component';
const routes: Routes = [
  {path: 'signup', component: RegisterComponent},
  {path:'',component:LoginComponent},
  //admin paths
  {path:'admin/jobs',component:JobListingsComponent},
  {path:'admin',component:ADashboardComponent},
  {path:'admin/jobs/:id',component:JobApplicationsComponent},
  //user paths
  {path:'user',component:DashboardComponent},
  {path:'user/resume',component:ResumeComponent},
  {path:'user/address',component:AddressComponent},
  {path:'user/jobs',component:JobsComponent},
  {path:'user/jobs/:id',component:JobApplicationComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
