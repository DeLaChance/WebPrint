import { Component, OnInit } from '@angular/core';

import { PrintingJob } from '../printing-job';

import { RestService } from '../rest.service';

@Component({
  selector: 'app-jobactivity',
  templateUrl: './jobactivity.component.html',
  styleUrls: ['./jobactivity.component.css']
})
export class JobactivityComponent implements OnInit {

  private activeJob: PrintingJob;

  constructor(private restService: RestService) { }

  ngOnInit() {
    this.restService.fetchActiveJob()
      .subscribe(activeJob => this.setActiveJob(activeJob));
  }

  setActiveJob(activeJob: PrintingJob) {
    this.activeJob = activeJob;
  }
}
