package org.lashly.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lashly.domain.dos.CollectionRecordDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionRecordDao {

	/**
	 * save data collection record
	 * 
	 * @param bo record entity
	 * @return ID
	 */
	Integer saveCollectionRecord(CollectionRecordDo bo);

	/**
	 * find collection records
	 *
	 * @param pageNumber page number
	 * @param pageSize page size
	 * @return collection record list
	 */
	List<CollectionRecordDo> findCollectionRecords(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
	
	/**
	 * count collection records
	 * 
	 * @return the number of collection records
	 */
	Integer countCollectionRecords();
	
}
