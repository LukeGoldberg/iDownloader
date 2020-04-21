package org.lashly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.lashly.domain.dtos.BookDto;
import org.lashly.domain.dtos.BookSearchDto;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookMapper {

	/**
	 * find books
	 * 
	 * @param bookSearchDto books query parameter
	 * @return books
	 */
    List<BookDto> findBooks(BookSearchDto bookSearchDto);
	
}
