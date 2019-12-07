package org.lashly.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespResult implements Serializable {

	private static final long serialVersionUID = -1L;

    private int code;

    private Object data;

    private String message;

}
