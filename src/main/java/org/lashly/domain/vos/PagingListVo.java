package org.lashly.domain.vos;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PagingListVo extends BaseVo {
	
	/**
	 * count of data
	 */
	private Integer count;
	
	/**
	 * data list
	 */
	private List<? extends Serializable> data;

}
