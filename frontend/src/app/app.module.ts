import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JobListingsComponent } from './core/components/job-listings/job-listings.component';
import { JobListingsService } from './core/services/job-listings.service';

@NgModule({
  declarations: [
    AppComponent,
    JobListingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, HttpClientModule
  ],
  providers: [JobListingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }