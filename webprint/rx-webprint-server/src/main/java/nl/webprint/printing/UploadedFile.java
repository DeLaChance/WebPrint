package nl.webprint.printing;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class UploadedFile {
	
	/** File name, e.g. 'abcd.txt' in '/var/tmp/abcd.txt' **/
	@JsonProperty("fileName")
	private String fileName;
	
	/** Full path (including name of uploaded file), e.g. /var/tmp/abcd.txt **/
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
