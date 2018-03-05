import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { FileDropModule, UploadFile, UploadEvent } from 'ngx-file-drop';

import { PrintingJob } from './printing-job'
import { RawPrintingJobList } from './raw-printing-job-list'

@Injectable()
export class RestService {

  private BASE_URL: string;
  private RX_BASE_URL: string;

  constructor(private http: HttpClient) {
    this.RX_BASE_URL = "http://localhost:8181";
  }

  fetchPrintingJobs(): Observable<RawPrintingJobList> {
    return this.http.get<RawPrintingJobList>(this.RX_BASE_URL + '/api/job');
  }

}
