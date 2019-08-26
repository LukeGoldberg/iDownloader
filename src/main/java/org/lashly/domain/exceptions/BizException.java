package org.lashly.domain.exceptions;

/**
 * a normal exception class
 */
public class BizException extends RuntimeException {
	
	private String msg;
	
	public BizException(String msg) {
		this.msg = msg;
	}

}
