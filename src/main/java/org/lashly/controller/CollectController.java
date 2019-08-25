package org.lashly.controller;

import org.lashly.domain.dto.BookSearchDto;
import org.lashly.domain.enums.CollectionEnum;
import org.lashly.service.FileGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {
	
	@Autowired
	private FileGenerateService fileGenerateService;
	
	@RequestMapping(value = "/book/collect")
	public void collectBooks(BookSearchDto bookSearchDto) {
		fileGenerateService.generateFile(CollectionEnum.BOOK, bookSearchDto);
	}
	
	
}
