package nl.webprint.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintingJob {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("created")
	private Long created;
	
	@JsonProperty("started")
	private Long started;
	
	@JsonProperty("completed")
	private Long completed;
	
	@JsonProperty("fileContents")
	private String fileContents;

	@JsonCreator
	PrintingJob(@JsonProperty("id") String id, @JsonProperty("name") String name, 
		@JsonProperty("created") Long created, @JsonProperty("started") Long started, 
		@JsonProperty("completed") Long completed, @JsonProperty("fileContents") String fileContents) {
		
		this.id = id;
		this.name = name;
		this.created = created;
		this.started = started;
		this.completed = completed;
		this.fileContents = fileContents;
	}
	
	private PrintingJob(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.created = builder.created;
		this.started = builder.started;
		this.completed = builder.completed;
		this.fileContents = builder.fileContents;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getCreated() {
		return created;
	}

	public Long getStarted() {
		return started;
	}

	public Long getCompleted() {
		return completed;
	}

	public String getFileContents() {
		return fileContents;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private String id;
		
		private String name;
		
		private Long created;
		
		private Long started;
		
		private Long completed;
		
		private String fileContents;
		
		private Builder() {
		}
		
		public Builder id(final String id) {
			this.id = id;
			return this;
		}
		
		public Builder name(final String name) {
			this.name = name;
			return this;
		}		
		
		public Builder created(final Long created) {
			this.created = created;
			return this;
		}
		
		public Builder started(final Long started) {
			this.started = started;
			return this;
		}
		
		public Builder completed(final Long completed) {
			this.completed = completed;
			return this;
		}		
		
		public Builder fileContents(final String fileContents) {
			this.fileContents = fileContents;
			return this;
		}
		
		public PrintingJob build() {
			return new PrintingJob(this);
		}
	}

}
