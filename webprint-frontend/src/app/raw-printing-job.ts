class RawIdentifier {
  identifier: string;
}

export class RawPrintingJob {
  identifier: RawIdentifier;
  fileName: string;
  createdTime: number;
  startedTime: number;
  completedTime: number;
}
