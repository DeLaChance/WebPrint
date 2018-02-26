import { RawPrintingJob } from './raw-printing-job'

export class PrintingJob {

  id: string;
  name: string;
  created: Date;
  started: Date;
  completed: Date;

  public static decode(rawPrintingJob: RawPrintingJob): PrintingJob {
    return {
      id: rawPrintingJob.identifier.identifier,
      name: rawPrintingJob.fileName,
      created: PrintingJob.toDate(rawPrintingJob.createdTime),
      started: PrintingJob.toDate(rawPrintingJob.startedTime),
      completed: PrintingJob.toDate(rawPrintingJob.completedTime)
    };
  }

  public static equals(p1 : PrintingJob, p2 : PrintingJob) {
    if( p1 && p2 && p1.id && p2.id ) {
      return p1.id === p2.id;
    }

    return false;
  }

  public toString() : String {
    return JSON.stringify(this);
  }

  static toDate(timeStamp: number): Date {
    return timeStamp ? new Date(timeStamp) : null;
  }

}
