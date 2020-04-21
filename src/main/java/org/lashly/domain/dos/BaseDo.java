package org.lashly.domain.dos;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * base data object
 */
@Data
class BaseDo implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * identity
	 */
	private Long id;
	
	/**
	 * create time
	 */
	private Date createTime;
	
	/**
	 * update time
	 */
	private Date updateTime;
	
}
