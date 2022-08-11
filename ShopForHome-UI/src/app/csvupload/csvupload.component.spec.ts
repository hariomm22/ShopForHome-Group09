import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsvuploadComponent } from './csvupload.component';

describe('CsvuploadComponent', () => {
  let component: CsvuploadComponent;
  let fixture: ComponentFixture<CsvuploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsvuploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CsvuploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
