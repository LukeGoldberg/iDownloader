package org.lashly.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * search result's dto
 */
@Data
public class SearchResultDto<T extends Serializable> {

	public T data;
	
}
