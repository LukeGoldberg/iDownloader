package org.lashly.service;

import org.lashly.domain.dto.SearchResultDto;

/**
 * to collect data
 */
public interface CollectionService {
	
	/**
	 * collect data
	 * 
	 * @param dto query
	 */
	<T> SearchResultDto collect(T dto);

}
