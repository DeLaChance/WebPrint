import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { FileDropModule, UploadFile, UploadEvent } from 'ngx-file-drop';

import { PrintingJob } from './printing-job'

class RawPrintingJob {
  id: string;
  name: string;
  created: number;
  started: number;
  completed: number;
  fileContents: string;
}

@Injectable()
export class RestService {

  private BASE_URL: string;

  constructor(private http: HttpClient) {
    this.BASE_URL = "http://localhost:8081"
  }

  fetchPrintingJobs() {
    return this.http.get<RawPrintingJob[]>(this.BASE_URL + '/api/job')
      .map(data => {
        console.log(data);
        var printingJobs: PrintingJob[] = [];

        data.printingJobs.forEach(rawPrintingJob => {
          var printingJob = PrintingJob.decode(rawPrintingJob);
          printingJobs.push(printingJob);
        });

        return printingJobs;
      });
  }

  fetchActiveJob() {
    //return this.http.get<PrintingJob[]>('/api/jobs?active=true');
    var mockJob = {
      id: "4",
      name: "name4",
      created: new Date(),
      started: new Date(),
      completed: null,
    };
    return Observable.of(mockJob);
  }

  postPrintingJob(file: UploadFile) {
    const formData = new FormData();
    formData.append("file", file.fileEntry);
    console.log("sending form " + formData);
    this.http.post('/api/jobs', formData);
  }
}
