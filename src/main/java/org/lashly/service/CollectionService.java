package org.lashly.service;

import org.lashly.domain.dtos.BaseDto;
import org.lashly.domain.dtos.SearchResultDto;

/**
 * to collect data
 */
public interface CollectionService {
	
	/**
	 * collect data
	 * 
	 * @param dto query
	 */
	SearchResultDto collect(BaseDto dto);

}
