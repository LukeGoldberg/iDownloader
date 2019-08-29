package org.lashly.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookDto extends BaseDto {

    private Long id;

    private String bookName;

}
