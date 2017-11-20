import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'

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
}
