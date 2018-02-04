package nl.webprint.configuration;

public enum AddressConfiguration {
	
	CONFIGURATION_REPOSITORY("configuration.repository");
	
	private final String address;
	
	AddressConfiguration(final String address) {
		this.address = address;
	}
	
	public final String getAddress() {
		return this.address;
	}

}
