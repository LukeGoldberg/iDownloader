package org.lashly.domain;

import lombok.Data;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

@Data
public final class RespResult implements Serializable {

	private static final long serialVersionUID = -1L;

    private int code;

    private Object data;

    private String message;
    
    public static RespResult sendSuccess() {
    	RespResult result = new RespResult();
    	result.setCode(HttpStatus.OK.value());
    	return result;
    }
    
    public static <T extends Serializable> RespResult sendData(T data) {
    	RespResult result = new RespResult();
    	result.setCode(HttpStatus.OK.value());
    	result.setData(data);
        return result;
    }
    
    public static RespResult sendError(HttpStatus httpStatus, String message) {
    	RespResult result = new RespResult();
    	result.setCode(httpStatus.value());
    	result.setMessage(message);
    	return result;
    }

}
