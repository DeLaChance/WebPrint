package nl.webprint.configuration;

public enum AddressConfiguration {
	
	CONFIGURATION_REPOSITORY("configuration-repository"),
	QUERY_PRINTING_JOB_SERVICE("priting-job-service:query"),
	ADD_PRINTING_JOB_SERVICE("priting-job-service:add"),
	DELETE_PRINTING_JOB_SERVICE("priting-job-service:delete")
		
	;	
	
	private final String address;
	
	AddressConfiguration(final String address) {
		this.address = address;
	}
	
	public final String getAddress() {
		return this.address;
	}

}
