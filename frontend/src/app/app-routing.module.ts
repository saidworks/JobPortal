import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobListingsComponent } from './core/admin/job-listings/job-listings.component';
import { LoginComponent } from './core/components/login/login.component';
import { RegisterComponent } from './core/components/register/register.component';
import { AddressComponent } from './core/user/address/address.component';
import { ResumeComponent } from './core/user/resume/resume.component';
import { JobsComponent } from './core/user/job-listings/jobs.component';
import { JobApplicationComponent } from './core/user/job-application/job-application.component';

const routes: Routes = [
  {path: 'signup', component: RegisterComponent},
  {path:'',component:LoginComponent},
  //admin paths
  {path:'home',component:JobListingsComponent},
  //user paths
  {path:'user/resume',component:ResumeComponent},
  {path:'user/address',component:AddressComponent},
  {path:'user/jobs',component:JobsComponent},
  {path:'user/jobs/:id',component:JobApplicationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
