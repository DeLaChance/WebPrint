package nl.webprint.printing;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Builder;
import lombok.Getter;

@DataObject
public class PrintingJobIdentifier {

	@JsonProperty("identifier")
	private UUID identifier;
	

	public PrintingJobIdentifier(final JsonObject object) {
		this.identifier = UUID.fromString(object.getString("identifier"));
	}
	
	private PrintingJobIdentifier(Builder builder) {
		this.identifier = builder.identifier;
	}

	public UUID getIdentifier() {
		return identifier;
	}
	
	public JsonObject toJson() {
		return new JsonObject()
			.put("identifier", identifier.toString());
	}
	
	public static PrintingJobIdentifier from(final UUID printingJobIdentifier) {
		return PrintingJobIdentifier
			.builder()
			.identifier(printingJobIdentifier)
			.build();
	}
	
	@Override
	public String toString() {
		return this.identifier.toString();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private UUID identifier;
		
		private Builder() {
			
		}
		
		public Builder identifier(final UUID identifier) {
			this.identifier = identifier;
			return this;
		}
		
		public PrintingJobIdentifier build() {
			return new PrintingJobIdentifier(this);
		}
		
	}
}
