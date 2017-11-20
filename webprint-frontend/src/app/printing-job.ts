export class PrintingJob {

  id: string;
  name: string;
  created: Date;
  started: Date;
  completed: Date;

  static encode(printingJob: PrintingJob): any {
    return {
      name: printingJob.name,
      id: printingJob.id,
      created: printingJob.created.toString(),
      started: printingJob.started.toString(),
      completed: printingJob.completed.toString()
    };
  }

  static decode(printingJobJson: any): PrintingJob {
    return {
      id: printingJobJson.id,
      name: printingJobJson.name,
      created: new Date(printingJobJson.created),
      started: new Date(printingJobJson.started),
      completed: new Date(printingJobJson.completed)
    };
  }
}
