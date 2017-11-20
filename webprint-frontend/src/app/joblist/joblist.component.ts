import { Component, OnInit } from '@angular/core';

import { PrintingJob } from '../printing-job';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-joblist',
  templateUrl: './joblist.component.html',
  styleUrls: ['./joblist.component.css']
})
export class JoblistComponent implements OnInit {

  // Variables
  printingJobs: PrintingJob[];

  // Methods
  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.fetchPrintingJobs().subscribe(data => this.setPrintingJobs(data));
  }

  setPrintingJobs(printingJobs: PrintingJob[]) {
    this.printingJobs = printingJobs;
    console.log("updated printingJobs " + printingJobs.length);
  }

}
