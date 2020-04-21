package org.lashly.controller;

import org.lashly.domain.RespResult;
import org.lashly.domain.dtos.BookSearchDto;
import org.lashly.domain.enums.CollectionEnum;
import org.lashly.service.FileGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * request for collect data
 */
@RestController
@RequestMapping(value = "/collect")
public class CollectController {
	
	private FileGenerateService fileGenerateService;

	@Autowired
	public CollectController(FileGenerateService fileGenerateService) {
		this.fileGenerateService = fileGenerateService;
	}
	
	@RequestMapping(value = "book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespResult collectBooks(@RequestBody BookSearchDto bookSearchDto) {
		fileGenerateService.generateFile(CollectionEnum.BOOK, bookSearchDto);
		return RespResult.sendSuccess();
	}

}
