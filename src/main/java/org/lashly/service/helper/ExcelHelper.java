package org.lashly.service.helper;

import org.lashly.domain.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExcelHelper {

	@Autowired
    private GridFsTemplate gridFsTemplate;
	
	/**
	 * generate excel
	 * 
	 * @param dto dto
	 * @return MongoDB ID
	 */
	public String generateExcel(SearchResultDto dto) {
		// generate excel;
        // multi Excel jar library can be used here
        Object workBook = new Object();

		// store into mongodb.
        // return gridFsTemplate.store(workBookInputStream, fileName).toString();
		return "2347823u9ryhshndfcwerf2iojfrihweoj";
	}
	
}
