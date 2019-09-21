package org.lashly.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lashly.domain.dto.BookDto;
import org.lashly.domain.dto.BookSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDao {

    List<BookDto> findBooks(BookSearchDto bookSearchDto);

}
