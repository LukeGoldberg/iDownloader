package org.lashly.service;

import org.junit.Test;
import org.lashly.ApplicationTests;
import org.lashly.domain.dtos.BookSearchDto;
import org.lashly.domain.enums.CollectionEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class FileGenerateServiceTest extends ApplicationTests {

	@Autowired
	private FileGenerateService fileGenerateService;
	
	@Test
	public void t() {
		BookSearchDto bookSearchDto = new BookSearchDto();
		bookSearchDto.setBookName("ring king");
		fileGenerateService.generateFile(CollectionEnum.BOOK, bookSearchDto);
	}
	
}
