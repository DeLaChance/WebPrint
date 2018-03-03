import { Component, OnInit } from '@angular/core';
import { DropzoneConfigInterface } from 'ngx-dropzone-wrapper';

@Component({
  selector: 'app-drag-and-drop-importer',
  templateUrl: './drag-and-drop-importer.component.html',
  styleUrls: ['./drag-and-drop-importer.component.css']
})
export class DragAndDropImporterComponent implements OnInit {

  public dropZoneConfig: DropzoneConfigInterface = {
    url: 'http://localhost:8181/api/job',
    maxFiles: 1,
    createImageThumbnails: false,
    autoReset: 2000
  };

  constructor() { }

  ngOnInit() {
  }

}
