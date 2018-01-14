package nl.webprint.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintingJobCreated {

	@JsonProperty("succeeded")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final Boolean succeeded;
	
	@JsonCreator
	PrintingJobCreated(@JsonProperty("succeeded") final Boolean succeeded) {
		this.succeeded = succeeded;
	}

	private PrintingJobCreated(final Builder builder) {
		this.succeeded = builder.succeeded;
	}

	public Boolean iSucceeded() {
		return this.succeeded;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Boolean succeeded;

		private Builder() {
		}

		public Builder succeeded(final Boolean succeeded) {
			this.succeeded = succeeded;
			return this;
		}

		public PrintingJobCreated build() {
			PrintingJobCreated request = new PrintingJobCreated(this);
			return request;
		}
	}	
	
}
