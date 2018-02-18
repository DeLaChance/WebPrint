import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { Headers } from '@angular/http';
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

class RawPrintingJobs {
  printingJobs: RawPrintingJob[];
}

@Injectable()
export class RestService {

  private BASE_URL: string;
  private RX_BASE_URL: string;

  constructor(private http: HttpClient) {
    this.BASE_URL = "http://localhost:8081";

    // Alternative verticle to test RxJava
    this.RX_BASE_URL = "http://localhost:8181";
  }

  fetchPrintingJobs() {
    return this.http.get<RawPrintingJobs>(this.RX_BASE_URL + '/api/job')
      .map(data => {
        var printingJobs: PrintingJob[] = [];

        data.printingJobs.forEach(rawPrintingJob => {
          var printingJob = PrintingJob.decode(rawPrintingJob);
          printingJobs.push(printingJob);
        });

        return printingJobs;
      });
  }

  fetchActiveJob() {
    return this.http.get<RawPrintingJobs>(this.RX_BASE_URL + '/api/job?active=true')
      .map(data => {
        if( data.printingJobs.length > 0 ) {
          return PrintingJob.decode(data.printingJobs[0]);
        } else {
          return null;
        }
      });
  }

}
