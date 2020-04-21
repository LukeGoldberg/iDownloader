package org.lashly.domain.vos;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * collection record display view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionRecordVo extends BaseVo {

	/**
	 * identity
	 */
	private Long id;
	
	/**
	 * file's name
	 */
	private String fileName;
	
	/**
	 * file's MongoID
	 */
	private String mongoId;
	
	/**
	 * file's state
	 */
	private String state;
	
	/**
	 * create time
	 */
	private Date createTime;
	
}
