package org.lashly.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lashly.domain.dto.BookDto;
import org.lashly.domain.dto.BookSearchDto;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookDao {

    BookDto findBooks(BookSearchDto bookSearchDto);

}
