package org.lashly.service.helper;

import org.lashly.domain.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExcelHelper {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void generateExcel(SearchResultDto dto) {
		// generate excel;
		// store into mongodb.
	}
	
}
