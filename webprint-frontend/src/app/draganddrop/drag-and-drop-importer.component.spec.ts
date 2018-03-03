import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragAndDropImporterComponent } from './drag-and-drop-importer.component';

describe('DragAndDropImporterComponent', () => {
  let component: DragAndDropImporterComponent;
  let fixture: ComponentFixture<DragAndDropImporterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragAndDropImporterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragAndDropImporterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
