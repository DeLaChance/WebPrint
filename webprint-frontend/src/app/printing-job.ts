export class PrintingJob {

  private id: string;
  private created: Date;
  private completed: Date;
  private name: string;

  constructor(id: string,
    created: Date,
    completed: Date,
    name: string) {
      this.id = id;
      this.created = created;
      this.completed = completed;
      this.name = name;
  }

  getId(): string {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getCreated(): Date {
    return this.created;
  }

  getCompleted(): Date {
    return this.completed;
  }

  encode(printingJob: PrintingJob): Any {
    return {
      name: printingJob.name,
      id: printingJob.id,
      created: printingJob.created.toString(),
      completed: printingJob.completed.toString()
    };
  }

  decode(printingJobJson: Any) {
    return {
      id: printingJobJson.id,
      name: printingJobJson.name,
      created: new Date(printingJobJson.created),
      completed: new Date(printingJobJson.completed)
    };
  }

}
