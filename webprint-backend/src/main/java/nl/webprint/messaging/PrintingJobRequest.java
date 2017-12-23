package nl.webprint.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A request for a printing job with an identifier.
 *
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class PrintingJobRequest {

	@JsonProperty("identifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final String identifier;

	@JsonCreator
	PrintingJobRequest(@JsonProperty("identifier") final String identifier) {
		this.identifier = identifier;
	}

	private PrintingJobRequest(final Builder builder) {
		this.identifier = builder.identifier;
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

		public PrintingJobRequest build() {
			PrintingJobRequest printingJobRequest = new PrintingJobRequest(this);
			return printingJobRequest;
		}
	}
}
