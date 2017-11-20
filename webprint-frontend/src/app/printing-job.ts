export class PrintingJob {

  id: string;
  name: string;
  created: Date;
  completed: Date;

  static encode(printingJob: PrintingJob): any {
    return {
      name: printingJob.name,
      id: printingJob.id,
      created: printingJob.created.toString(),
      completed: printingJob.completed.toString()
    };
  }

  static decode(printingJobJson: any): PrintingJob {
    return {
      id: printingJobJson.id,
      name: printingJobJson.name,
      created: new Date(printingJobJson.created),
      completed: new Date(printingJobJson.completed)
    };
  }
}
