import { Injectable } from '@angular/core';

import { PrintingJob } from './printing-job'

@Injectable()
export abstract class DataListener {

  abstract update(printingJobs: PrintingJob[]);

}
