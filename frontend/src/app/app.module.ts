import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobListingsComponent } from './core/admin/job-listings/job-listings.component';
import { JobListingsService } from './core/admin/services/job-listings.service';
import { HeaderComponent } from './core/components/header/header.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterComponent } from './core/components/register/register.component';
import { LoginComponent } from './core/components/login/login.component';
import { AuthService } from './core/services/auth.service';
import { AuthInterceptorService } from './core/services/auth-interceptor.service';
import { ResumeComponent } from './core/user/resume/resume.component';
import { ResumeService } from './core/user/services/resume.service';
import { AddressComponent } from './core/user/address/address.component';
import { DashboardComponent } from './core/user/dashboard/dashboard.component';
import { JobApplicationComponent } from './core/user/job-application/job-application.component';
import { JobListingDetailComponent } from './core/user/job-listings/job-listing-detail/job-listing-detail.component';
import { AddressService } from './core/user/services/address.service';
import { JobsComponent } from './core/user/job-listings/jobs.component';



@NgModule({
  declarations: [
    AppComponent,
    JobListingsComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ResumeComponent,
    AddressComponent,
    DashboardComponent,
    JobApplicationComponent,
    JobListingDetailComponent,
    JobsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, HttpClientModule, NgbModule,
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},
              JobListingsService, AuthService,ResumeService,AddressService],
  bootstrap: [AppComponent]
})
export class AppModule { }
