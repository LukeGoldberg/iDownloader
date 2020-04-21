package org.lashly.domain.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * search result's dto
 */
@Data
public class SearchResultDto<T extends Serializable> {

	private List<T> data;

	private String fileName;

	private String[] headers;

	private String sheetName;

}
