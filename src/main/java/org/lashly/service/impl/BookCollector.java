package org.lashly.service.impl;

import java.util.List;

import org.lashly.dao.BookDao;
import org.lashly.domain.dtos.BaseDto;
import org.lashly.domain.dtos.BookDto;
import org.lashly.domain.dtos.BookSearchDto;
import org.lashly.domain.dtos.SearchResultDto;
import org.lashly.service.CollectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCollector implements CollectionService {

	/**
	 * book's dao
	 */
    private BookDao bookDao;

    @Autowired
    public BookCollector(BookDao bookDao) {
        this.bookDao = bookDao;
    }

	@Override
	public SearchResultDto<BookDto> collect(BaseDto dto) {
		BookSearchDto bookSearchDto = new BookSearchDto();
		BeanUtils.copyProperties(dto, bookSearchDto);
        List<BookDto> bookDto = bookDao.findBooks(bookSearchDto);
        SearchResultDto<BookDto> result = new SearchResultDto<>();
        result.setFileName("book");
        result.setSheetName("book sheet");
        result.setHeaders(new String[]{"id", "book name"});
        result.setData(bookDto);
        return result;
	}
	
}
