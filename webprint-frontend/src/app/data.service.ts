import { Injectable } from '@angular/core';

import { PrintingJob } from './printing-job';
import { DataListener } from './data-listener';
import { RawPrintingJobList } from './raw-printing-job-list'

@Injectable()
export class DataService {

  printingJobs: PrintingJob[];
  dataListeners: DataListener[];

  constructor() {
    this.dataListeners = [];
  }

  addListener(listener: DataListener) {
    this.dataListeners.push(listener);
  }

  callListeners() {
    this.dataListeners.forEach(listener => {
      listener.update(this.printingJobs);
    });
  }

  loadPrintingJobs(rawPrintingJobs: RawPrintingJobList) {
    this.setPrintingJobs(this.unwrap(rawPrintingJobs));
    this.callListeners();
  }

  unwrap(rawPrintingJobs: RawPrintingJobList): PrintingJob[] {
    var printingJobs: PrintingJob[] = [];

    rawPrintingJobs.jobs.forEach(rawPrintingJob => {
      var printingJob = PrintingJob.decode(rawPrintingJob);
      printingJobs.push(printingJob);
    });

    return printingJobs;
  }

  setPrintingJobs(printingJobs: PrintingJob[]) {
    this.printingJobs = printingJobs;
  }

  getPrintingJobs(): PrintingJob[] {
    return this.printingJobs;
  }
}
