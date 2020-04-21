package org.lashly.domain.dos;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * data collection data object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionRecordDo extends BaseDo {

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
	
}
