package org.lashly.domain.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * base bo
 */
@Data
public class BaseBo implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private Long id;
	
	private Date createTime;
	
	private Date updateTime;
	
}
