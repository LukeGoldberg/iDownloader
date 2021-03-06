package org.lashly.dao;

import java.util.List;

import org.lashly.domain.dtos.BookDto;
import org.lashly.domain.dtos.BookSearchDto;

public interface BookDao {

	/**
	 * find books, parameter is checked
	 * 
	 * @param bookSearchDto books query parameter
	 * @return books
	 */
    List<BookDto> findBooks(BookSearchDto bookSearchDto);

}
