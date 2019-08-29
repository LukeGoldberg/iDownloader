package org.lashly.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * search result's dto
 */
@Data
public class SearchResultDto<T extends Serializable> {

	public T data;
	
}
