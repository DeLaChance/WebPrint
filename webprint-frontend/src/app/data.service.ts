import { Injectable } from '@angular/core';

import { PrintingJob } from './printing-job';
import { DataListener } from './data-listener';
import { RawPrintingJobList } from './raw-printing-job-list';
import { RawPrintingJob } from './raw-printing-job';

import { Message } from '@stomp/stompjs';
import { StompService } from '@stomp/ng2-stompjs';
import './rxjs-operators';

@Injectable()
export class DataService {

  printingJobs: PrintingJob[];
  dataListeners: DataListener[];
  stomp_subscription: any;

  constructor(private _stompService: StompService) {
    this.dataListeners = [];

    this.stomp_subscription = this._stompService.subscribe('notifications.printing-job');

    this.stomp_subscription.map((message: Message) => {
      return message.body;
    }).subscribe((msg_body: string) => {
      var json = JSON.parse(msg_body);
      var printingJob = PrintingJob.decodeJson(json);
      this.updateSingle(printingJob);
    });
  }

  addListener(listener: DataListener) {
    this.dataListeners.push(listener);
  }

  callListeners() {
    this.dataListeners.forEach(listener => {
      listener.update(this.printingJobs);
    });
  }

  updateSingle(printingJob : PrintingJob) {
    for( var i = 0; i < this.printingJobs.length; i++) {
      if( PrintingJob.equals(this.printingJobs[i], printingJob) ) {
        this.printingJobs[i] = printingJob;
        this.callListeners();
        break;
      }
    }
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
