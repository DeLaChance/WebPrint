package nl.webprint.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintingJobDeletionRequest {

	@JsonProperty("identifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String identifier;
	
	@JsonCreator
	PrintingJobDeletionRequest(@JsonProperty("identifier") final String identifier) {
		this.identifier = identifier;
	}

	private PrintingJobDeletionRequest(final Builder builder) {
		this.identifier = builder.identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String identifier;

		private Builder() {
		}

		public Builder identifier(String identifier) {
			this.identifier = identifier;
			return this;
		}

		public PrintingJobDeletionRequest build() {
			PrintingJobDeletionRequest request = new PrintingJobDeletionRequest(this);
			return request;
		}
	}
	
}
