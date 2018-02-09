package nl.webprint.adapter.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class FileUpload {
	
	@JsonProperty("fullFilePath")
	private String fullFilePath;
	
	@JsonProperty("fileName")
	private String fileName;
	
	@JsonProperty("fileEncoding")
	private String fileEncoding;

	public FileUpload(final JsonObject jsonObject) {
		this.fullFilePath = jsonObject.getString("fullFilePath");
		this.fileName = jsonObject.getString("fileName");
		this.fileEncoding = jsonObject.getString("fileEncoding");
	}
	
	private FileUpload(Builder builder) {
		this.fileEncoding = builder.fileEncoding;
		this.fileName = builder.fileName;
		this.fullFilePath = builder.fullFilePath;
	}

	public JsonObject toJson() {
		return JsonObject.mapFrom(this);
	}
	
	public String getFullFilePath() {
		return fullFilePath;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileEncoding() {
		return fileEncoding;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static FileUpload from(final io.vertx.ext.web.FileUpload fileUpload) {
		return FileUpload.builder()
			.fileEncoding(fileUpload.contentType())
			.fileName(fileUpload.fileName())
			.fullFilePath(fileUpload.uploadedFileName())
			.build();
	}
	
	public static class Builder {
		
		private String fullFilePath;
		private String fileName;
		private String fileEncoding;
		
		private Builder() {
		}
		
		public Builder fullFilePath(final String fullFilePath) {
			this.fullFilePath = fullFilePath;
			return this;
		}
		
		public Builder fileName(final String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public Builder fileEncoding(final String fileEncoding) {
			this.fileEncoding = fileEncoding;
			return this;
		}
		
		public FileUpload build() {
			return new FileUpload(this);
		}
		
	} 
}
