package nl.webprint.util;

import java.util.UUID;

public class UUIDConverter {
	
	private UUIDConverter() {		
	}
	
	public static boolean canConvert(final String uuidString) {
		
		boolean canConvert;
		try {
			UUID.fromString(uuidString);
			canConvert = true;
		} catch(final Exception e) {
			canConvert = false;
		}
		
		return canConvert;
	}

	public static UUID convert(final String uuidString) {
		return UUID.fromString(uuidString);
	}
	
}

