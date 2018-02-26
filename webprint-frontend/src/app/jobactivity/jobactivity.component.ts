import { Component, OnInit } from '@angular/core';

import { PrintingJob } from '../printing-job';

import { DataService } from '../data.service';
import { DataListener } from '../data-listener';

@Component({
  selector: 'app-jobactivity',
  templateUrl: './jobactivity.component.html',
  styleUrls: ['./jobactivity.component.css']
})
export class JobactivityComponent implements OnInit, DataListener {

  private activeJob: PrintingJob;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.dataService.addListener(this);
  }

  update(printingJobs: PrintingJob[]) {
    printingJobs.forEach(printingJob => {
      if( printingJob.started !== null && printingJob.completed === null ) {
        this.setActiveJob(printingJob);
        return;
      }
    });
  }

  setActiveJob(activeJob: PrintingJob) {
    this.activeJob = activeJob;
  }
}
