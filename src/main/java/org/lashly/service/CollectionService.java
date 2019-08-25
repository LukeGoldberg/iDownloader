package org.lashly.service;

import org.lashly.domain.dto.SearchResultDto;

/**
 * 
 *
 */
public interface CollectionService {
	
	/**
	 * 
	 * @param dto
	 */
	<T> SearchResultDto collect(T dto);

}
