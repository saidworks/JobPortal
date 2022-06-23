import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobListingsComponent } from './core/admin/job-listings/job-listings.component';
import { LoginComponent } from './core/components/login/login.component';
import { RegisterComponent } from './core/components/register/register.component';
import { ResumeComponent } from './core/user/resume/resume.component';

const routes: Routes = [
  {path: 'signup', component: RegisterComponent},
  {path:'',component:LoginComponent},
  {path:'home',component:JobListingsComponent},
  {path:'user/resume',component:ResumeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
