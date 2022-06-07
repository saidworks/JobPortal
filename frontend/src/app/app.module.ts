import { HttpClientModule } from '@angular/common/http';
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




@NgModule({
  declarations: [
    AppComponent,
    JobListingsComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, HttpClientModule, NgbModule,
    ReactiveFormsModule
  ],
  providers: [JobListingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
