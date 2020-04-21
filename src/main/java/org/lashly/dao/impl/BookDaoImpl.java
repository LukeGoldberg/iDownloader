package org.lashly.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.lashly.dao.BookDao;
import org.lashly.domain.dtos.BookDto;
import org.lashly.domain.dtos.BookSearchDto;
import org.lashly.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BookDaoImpl implements BookDao {

	/**
	 * book mapper for db query
	 */
	private BookMapper bookMapper;
	
	@Autowired
	public BookDaoImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	@Override
	public List<BookDto> findBooks(BookSearchDto bookSearchDto) {
        if (Objects.isNull(bookSearchDto) || StringUtils.isBlank(bookSearchDto.getBookName()) ) {
        	log.error("book search query parameter is empty");
        	return new ArrayList<>();
        }
		return bookMapper.findBooks(bookSearchDto);
	}

}
