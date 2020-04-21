package org.lashly.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * book's search data transfer object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookSearchDto extends BaseDto {

    /**
     * book's name
     */
    @NotEmpty(message = "input book's name for query")
    private String bookName;

}
