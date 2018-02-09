package nl.webprint.printing;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class PrintingJob {

	@JsonProperty("identifier")
	private PrintingJobIdentifier identifier;
	
	@JsonProperty("fileName")
	private String fileName;
	
	@JsonProperty("filePath")
	private String filePath;
	
	/** Time this printing job was created. Unix timestamp (ms) **/
	@JsonProperty("createdTime")
	private Long createdTime;
	
	/** Time this printing job was started. Unix timestamp (ms) **/
	@JsonProperty("startedTime")
	private Long startedTime;
	
	/** Time this printing job was completed. Unix timestamp (ms) **/
	@JsonProperty("completedTime")
	private Long completedTime;
	
	public PrintingJob(final JsonObject object) {
		this.identifier = new PrintingJobIdentifier(object.getJsonObject("identifier"));
		this.fileName = object.getString("fileName");
		this.filePath = object.getString("filePath");
		this.createdTime = object.getLong("createdTime");
		this.startedTime = object.getLong("startedTime");
		this.completedTime = object.getLong("completedTime");
	}
	
	public PrintingJob(Builder builder) {
		this.identifier = builder.identifier;
		this.fileName = builder.fileName;
		this.filePath = builder.filePath;
		this.createdTime = builder.createdTime;
		this.startedTime = builder.startedTime;
		this.completedTime = builder.completedTime;
	}

	public PrintingJobIdentifier getIdentifier() {
		return identifier;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public Long getStartedTime() {
		return startedTime;
	}

	public Long getCompletedTime() {
		return completedTime;
	}
	
	public JsonObject toJson() {
		return JsonObject.mapFrom(this);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private PrintingJobIdentifier identifier;
		private String fileName;
		private String filePath;
		private Long createdTime;
		private Long startedTime;
		private Long completedTime;
		
		private Builder() {
			
		}
		
		public Builder identifier(final PrintingJobIdentifier identifier) {
			this.identifier = identifier;
			return this;
		}
		
		public Builder fileName(final String fileName) {
			this.fileName = fileName;
			return this;
		}

		public Builder filePath(final String filePath) {
			this.filePath = filePath;
			return this;
		}		
		
		public Builder createdTime(final Long createdTime) {
			this.createdTime = createdTime;
			return this;
		}
		
		public Builder startedTime(final Long startedTime) {
			this.startedTime = startedTime;
			return this;
		}
		
		public Builder completedTime(final Long completedTime) {
			this.completedTime = completedTime;
			return this;
		}		
		
		public PrintingJob build() {
			return new PrintingJob(this);
		}
		
	}	
}
