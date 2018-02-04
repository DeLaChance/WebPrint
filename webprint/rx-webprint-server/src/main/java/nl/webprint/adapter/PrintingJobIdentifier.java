package nl.webprint.adapter;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Builder;

@DataObject
@Builder
public class PrintingJobIdentifier {

	public PrintingJobIdentifier(final JsonObject object) {
		
	}
	
	public JsonObject toJson() {
		return JsonObject.mapFrom(this);
	}
}
