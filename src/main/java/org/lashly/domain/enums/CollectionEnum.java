package org.lashly.domain.enums;

/**
 * enumeration for map collection service, 
 * the serviceName field should be the name of 
 * a sub class implemented <code>CollectionService</code>
 */
public enum CollectionEnum {
	
	BOOK("bookCollector");
	
	/**
	 * collector service's name
	 */
	private String serviceName;
	
	CollectionEnum(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}

}
