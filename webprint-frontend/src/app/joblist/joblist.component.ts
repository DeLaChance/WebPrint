import { Component, OnInit } from '@angular/core';
import { PrintingJob } from '../printing-job';

@Component({
  selector: 'app-joblist',
  templateUrl: './joblist.component.html',
  styleUrls: ['./joblist.component.css']
})
export class JoblistComponent implements OnInit {

  // Variables
  printingJobs: PrintingJob[];

  // Methods
  constructor() {
    this.printingJobs = [
      {
        id: "1",
        name: "name",
        created: new Date(),
        completed: new Date()
      }
    ];

  }

  ngOnInit() {
  }

}
