import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ATMsListComponent } from './atms-list.component';

describe('ATMsListComponent', () => {
  let component: ATMsListComponent;
  let fixture: ComponentFixture<ATMsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ATMsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ATMsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
