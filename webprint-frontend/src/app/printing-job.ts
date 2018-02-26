import { RawPrintingJob } from './raw-printing-job'

export class PrintingJob {

  id: string;
  name: string;
  created: Date;
  started: Date;
  completed: Date;

  public static decodeJson(json: any): PrintingJob {
    return {
      id: json.identifier.identifier,
      name: json.fileName,
      created: PrintingJob.toDate(json.createdTime),
      started: PrintingJob.toDate(json.startedTime),
      completed: PrintingJob.toDate(json.completedTime)
    };
  }

  public static decode(rawPrintingJob: RawPrintingJob): PrintingJob {
    return {
      id: rawPrintingJob.identifier.identifier,
      name: rawPrintingJob.fileName,
      created: PrintingJob.toDate(rawPrintingJob.createdTime),
      started: PrintingJob.toDate(rawPrintingJob.startedTime),
      completed: PrintingJob.toDate(rawPrintingJob.completedTime)
    };
  }

  public static equals(p1 : PrintingJob, p2 : PrintingJob) : boolean {
    if( p1 && p2 && p1.id && p2.id ) {
      return p1.id === p2.id;
    }

    return false;
  }

  public static toString(p1 : PrintingJob) : String {
    return JSON.stringify(p1);
  }

  static toDate(timeStamp: number): Date {
    return timeStamp ? new Date(timeStamp) : null;
  }

}
