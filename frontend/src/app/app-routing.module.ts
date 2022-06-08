import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobListingsComponent } from './core/components/job-listings/job-listings.component';
import { RegisterComponent } from './core/components/register/register.component';

const routes: Routes = [
  {path: 'signup', component: RegisterComponent},
  {path:'',component:JobListingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
