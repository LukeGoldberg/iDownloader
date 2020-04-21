package org.lashly.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookDto extends BaseDto {

	/**
	 * 
	 */
    private Long id;

    /**
     * book's name
     */
    private String bookName;

}
