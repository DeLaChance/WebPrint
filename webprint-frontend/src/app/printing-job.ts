import { RawPrintingJob } from './raw-printing-job'

export class PrintingJob {

  id: string;
  name: string;
  created: Date;
  started: Date;
  completed: Date;

  static decode(rawPrintingJob: RawPrintingJob): PrintingJob {
    return {
      id: rawPrintingJob.identifier.identifier,
      name: rawPrintingJob.fileName,
      created: new Date(rawPrintingJob.createdTime),
      started: new Date(rawPrintingJob.startedTime),
      completed: new Date(rawPrintingJob.completedTime)
    };
  }

}
