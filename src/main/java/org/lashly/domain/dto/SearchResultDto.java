package org.lashly.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * search result's dto
 */
@Data
public class SearchResultDto<T extends Serializable> {

	private Collection<T> data;

	private String fileName;

	private String[] headers;

	private String sheetName;

}
