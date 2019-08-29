package org.lashly.domain.enums;

/**
 * enum for map collection service, serviceName should be the name of 
 * a sub class implemented <code>CollectionService</code>
 */
public enum CollectionEnum {
	
	BOOK("bookCollector");
	
	private String serviceName;
	
	CollectionEnum(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}

}
