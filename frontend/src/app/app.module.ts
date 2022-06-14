import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobListingsComponent } from './core/components/job-listings/job-listings.component';
import { JobListingsService } from './core/services/job-listings.service';
import { HeaderComponent } from './core/components/header/header.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterComponent } from './core/components/register/register.component';
import { LoginComponent } from './core/components/login/login.component';
import { AuthService } from './core/services/auth.service';
import { AuthInterceptorService } from './core/services/auth-interceptor.service';
import { ResumeComponent } from './core/components/resume/resume.component';



@NgModule({
  declarations: [
    AppComponent,
    JobListingsComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    ResumeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, HttpClientModule, NgbModule,
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
