package org.lashly.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CollectionRecordBo extends BaseBo {

	private String fileName;
	
	private String mongoId;
	
}
