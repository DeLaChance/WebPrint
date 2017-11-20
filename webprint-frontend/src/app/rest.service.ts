import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { FileDropModule, UploadFile, UploadEvent } from 'ngx-file-drop';

import { PrintingJob } from './printing-job'

@Injectable()
export class RestService {

  constructor(private http: HttpClient) { }

  fetchPrintingJobs() {
    //return this.http.get<PrintingJob[]>('/api/jobs');
    var mockData = [
        {
          id: "1",
          name: "name1",
          created: new Date(0),
          completed: new Date(1)
        },
        {
          id: "2",
          name: "name2",
          created: new Date(2),
          completed: new Date(3)
        },
        {
          id: "3",
          name: "name3",
          created: new Date(4),
          completed: new Date(5)
        }
    ];
    return Observable.of(mockData);
  }

  postPrintingJob(file: UploadFile) {
    const formData = new FormData();
    formData.append("file", file.fileEntry);
    console.log("sending form " + formData);
    this.http.post('/api/jobs', formData);
  }
}
