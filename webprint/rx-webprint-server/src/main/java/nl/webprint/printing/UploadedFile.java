package nl.webprint.printing;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class UploadedFile {
	
	@JsonProperty("fileName")
	private String fileName;
	
	@JsonProperty("uploadedFilePath")
	private String uploadedFilePath;
	
	public UploadedFile(final JsonObject object) {
		this.fileName = object.getString("fileName");
		this.uploadedFilePath = object.getString("uploadedFilePath");
	}

	public String getFileName() {
		return fileName;
	}

	public String getUploadedFilePath() {
		return uploadedFilePath;
	}
	
	public JsonObject toJson() {
		return JsonObject.mapFrom(this);
	}

}
