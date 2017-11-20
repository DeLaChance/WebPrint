import { Component, OnInit } from '@angular/core';
import { FileDropModule, UploadFile, UploadEvent } from 'ngx-file-drop';

import { RestService } from '../rest.service';

@Component({
  selector: 'app-draganddrop',
  templateUrl: './draganddrop.component.html',
  styleUrls: ['./draganddrop.component.css']
})
export class DraganddropComponent implements OnInit {

  public files: UploadFile[] = [];

  constructor(private restService: RestService) { }

  ngOnInit() {
  }

  public dropped(event: UploadEvent) {
    this.files = event.files;
    for (var file of event.files) {
      file.fileEntry.file(info => {
        console.log(info);
      });
    }
  }

  public fileOver(event){
    console.log(event);
  }

  public fileLeave(event){
    console.log(event);
  }

  public uploadFiles() {
    for(let file of this.files) {
      this.restService.postPrintingJob(file);
    }
  }

}
