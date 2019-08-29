package org.lashly.service.helper;

import org.lashly.domain.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExcelHelper {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * generate excel
	 * 
	 * @param dto dto
	 * @return MongoDB ID
	 */
	public String generateExcel(SearchResultDto dto) {
		// generate excel;
		// store into mongodb.
		return "2347823u9ryhshndfcwerf2iojfrihweoj";
	}
	
}
