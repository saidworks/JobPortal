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
import { LogoutComponent } from './core/components/logout/logout.component';
import { UsersComponent } from './core/admin/users/users.component';
import { PagenotfoundComponent } from './core/components/pagenotfound/pagenotfound.component';
import { AuthGuardGuard } from './core/services/auth-guard.guard';
const routes: Routes = [
  {path: 'signup', component: RegisterComponent},
  {path:'',component:LoginComponent},
  {path:'logout',component:LogoutComponent},
  //admin paths
  {path:'admin/jobs',component:JobListingsComponent,canActivate:[AuthGuardGuard]},
  {path:'admin',component:ADashboardComponent,canActivate:[AuthGuardGuard]},
  {path:'admin/jobs/:id',component:JobApplicationsComponent,canActivate:[AuthGuardGuard]},
  {path:'admin/users',component:UsersComponent,canActivate:[AuthGuardGuard]},
  //user paths
  {path:'user',component:DashboardComponent,canActivate:[AuthGuardGuard]},
  {path:'user/resume',component:ResumeComponent,canActivate:[AuthGuardGuard]},
  {path:'user/address',component:AddressComponent,canActivate:[AuthGuardGuard]},
  {path:'user/jobs',component:JobsComponent,canActivate:[AuthGuardGuard]},
  {path:'user/jobs/:id',component:JobApplicationComponent,canActivate:[AuthGuardGuard]},
  { path: '**', component: PagenotfoundComponent,canActivate:[AuthGuardGuard] },  // Wildcard route for a 404 page,
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
