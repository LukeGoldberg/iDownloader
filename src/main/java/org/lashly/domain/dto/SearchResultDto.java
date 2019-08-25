package org.lashly.domain.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchResultDto<T extends Serializable> {

	public T data;
	
}
