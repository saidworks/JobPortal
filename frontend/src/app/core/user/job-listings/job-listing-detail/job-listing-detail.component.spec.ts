import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobListingDetailComponent } from './job-listing-detail.component';

describe('JobListingDetailComponent', () => {
  let component: JobListingDetailComponent;
  let fixture: ComponentFixture<JobListingDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobListingDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobListingDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
