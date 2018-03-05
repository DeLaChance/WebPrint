import { Component, OnInit } from '@angular/core';

import { PrintingJob } from '../printing-job';
import { RawPrintingJobList } from '../raw-printing-job-list'
import { RestService } from '../rest.service';
import { DataService } from '../data.service';
import { DataListener } from '../data-listener';

import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-joblist',
  templateUrl: './joblist.component.html',
  styleUrls: ['./joblist.component.css']
})
export class JoblistComponent implements OnInit, DataListener {

  // Variables
  printingJobs: PrintingJob[];
  columnsToDisplay = [ "identifier", "name", "created", "started", "completed" ];

  // Methods
  constructor(private restService: RestService,
    private dataService: DataService) {
  }

  ngOnInit() {
    this.dataService.addListener(this);
  }

  update(printingJobs: PrintingJob[]) {
    this.printingJobs = printingJobs;
  }


}
