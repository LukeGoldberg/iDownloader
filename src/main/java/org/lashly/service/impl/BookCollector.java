package org.lashly.service.impl;

import org.lashly.domain.dto.BookDto;
import org.lashly.domain.dto.SearchResultDto;
import org.lashly.service.CollectionService;
import org.springframework.stereotype.Service;

@Service
public class BookCollector implements CollectionService {

	@Override
	public <T> SearchResultDto<BookDto> collect(T dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
