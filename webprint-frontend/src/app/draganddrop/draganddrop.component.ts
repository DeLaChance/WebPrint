import { Component, OnInit } from '@angular/core';
import { FileDropModule, UploadFile, UploadEvent } from 'ngx-file-drop';

import { RestService } from '../rest.service';

import { MatDialog } from '@angular/material'

import { DragAndDropImporterComponent } from './drag-and-drop-importer.component'

@Component({
  selector: 'app-draganddrop',
  templateUrl: './draganddrop.component.html',
  styleUrls: ['./draganddrop.component.css']
})
export class DraganddropComponent implements OnInit {

  constructor(private dialog: MatDialog) {

  }

  ngOnInit() {
  }

  public addPrintingJob() {
    this.dialog.open(DragAndDropImporterComponent, {
      minHeight: '160px',
      minWidth: '600px'
    });
  }

}
