package nl.webprint.adapter;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Builder;

@DataObject
@Builder
public class PrintingJob {

	
	public PrintingJob(final JsonObject object) {
		
	}
	
	public JsonObject toJson() {
		return JsonObject.mapFrom(this);
	}
}
