package org.lashly.dao;

import org.junit.Test;
import org.lashly.ApplicationTests;
import org.lashly.domain.dtos.BookSearchDto;
import org.springframework.beans.factory.annotation.Autowired;

public class BookDaoTest extends ApplicationTests {
	
	@Autowired
	private BookDao bookDao;
	
	@Test
	public void findBooksTest() {
		BookSearchDto bookSearchDto = new BookSearchDto();
		bookSearchDto.setBookName("bookName");
		bookDao.findBooks(bookSearchDto);
	}

}
