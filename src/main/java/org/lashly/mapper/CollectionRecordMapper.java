package org.lashly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.vos.CollectionRecordVo;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CollectionRecordMapper {

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
	List<CollectionRecordVo> findCollectionRecords(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
	
	/**
	 * count collection records
	 * 
	 * @return the number of collection records
	 */
	Integer countCollectionRecords();
	
}
