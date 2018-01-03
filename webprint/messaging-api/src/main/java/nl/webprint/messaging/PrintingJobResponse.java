package nl.webprint.messaging;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintingJobResponse {
	
	@JsonProperty("printingJobs")
	private List<PrintingJob> printingJobs;
	
	private PrintingJobResponse(final Builder builder) {
		this.printingJobs = builder.printingJobs;
	}
	
	@JsonCreator
	PrintingJobResponse(@JsonProperty("printingJobs") final List<PrintingJob> printingJobs) {
		this.printingJobs = printingJobs;
	}	
	
	public List<PrintingJob> getPrintingJobs() {
		return printingJobs;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		
		private List<PrintingJob> printingJobs;
		
		private Builder() {	
		}
		
		public Builder printingJobs(final List<PrintingJob> printingJobs) {
			this.printingJobs = printingJobs;
			return this;
		}
		
		public PrintingJobResponse build() {
			return new PrintingJobResponse(this);
		}
		
	}
}
