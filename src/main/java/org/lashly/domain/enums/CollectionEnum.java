package org.lashly.domain.enums;

/**
 * 
 */
public enum CollectionEnum {
	
	BOOK("BookCollector");
	
	private String serviceName;
	
	CollectionEnum(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}

}
