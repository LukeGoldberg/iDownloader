package org.lashly.domain.dos;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * data collection dos
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionRecordDo extends BaseDo {

	private String fileName;
	
	private String mongoId;
	
}
