package org.lashly.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PagingListDto extends BaseDto {
	
	private Integer count;
	
	private List<? extends Serializable> data;

}
