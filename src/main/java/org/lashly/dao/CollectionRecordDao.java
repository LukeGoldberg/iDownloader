package org.lashly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.vos.CollectionRecordVo;

public interface CollectionRecordDao {

	/**
	 * save data collection record
	 * 
	 * @param do record entity
	 * @return ID
	 */
	Integer saveCollectionRecord(CollectionRecordDo collectionReordDo);

	/**
	 * find collection records
	 *
	 * @param pageNumber page number
	 * @param pageSize page size
	 * @return collection record list
	 */
	List<CollectionRecordVo> findCollectionRecords(Integer pageNumber, @Param("pageSize") Integer pageSize);
	
	/**
	 * count collection records
	 * 
	 * @return the number of collection records
	 */
	Integer countCollectionRecords();
	
}
