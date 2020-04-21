package org.lashly.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class DownloadRecordListDto extends BaseDto {

	private static final long serialVersionUID = -1L;

	@NotNull(message = "page number can not be empty")
    @Min(value = 0, message = "page number can not smaller than zero")
    @Max(value = 100, message = " page number can not bigger than one hundred")
    private Integer pageNumber;

    @NotNull(message = "page size can not be empty")
    @Min(value = 0, message = "page size must bigger than zero")
    @Max(value = 100, message = " page size can not bigger than one hundred")
    private Integer pageSize;

}
