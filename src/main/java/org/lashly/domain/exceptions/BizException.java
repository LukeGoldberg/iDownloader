package org.lashly.domain.exceptions;

/**
 * a normal exception class
 */
public class BizException extends RuntimeException {
	
	private String info;
	
	public BizException(String info) {
		this.info = info;
	}

	public String getInfo() {
	    return info;
    }

}
