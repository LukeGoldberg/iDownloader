package org.lashly.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lashly.domain.bo.CollectionRecordBo;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CollectionRecordDao {

	/**
	 * save data collection record
	 * 
	 * @param bo record entity
	 * @return ID
	 */
	Integer saveCollectionRecord(CollectionRecordBo bo);
	
}
