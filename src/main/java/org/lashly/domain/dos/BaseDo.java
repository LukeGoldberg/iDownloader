package org.lashly.domain.dos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * base dos
 */
@Data
class BaseDo implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private Long id;
	
	private Date createTime;
	
	private Date updateTime;
	
}
