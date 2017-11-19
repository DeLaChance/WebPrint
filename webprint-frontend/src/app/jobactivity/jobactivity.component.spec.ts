import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobactivityComponent } from './jobactivity.component';

describe('JobactivityComponent', () => {
  let component: JobactivityComponent;
  let fixture: ComponentFixture<JobactivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobactivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobactivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
