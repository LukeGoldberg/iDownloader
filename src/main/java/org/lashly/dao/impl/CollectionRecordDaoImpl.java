package org.lashly.dao.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.vos.CollectionRecordVo;
import org.lashly.mapper.CollectionRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionRecordDaoImpl implements CollectionRecordDao {

	private CollectionRecordMapper collectionRecordMapper;
	
	@Autowired
	public CollectionRecordDaoImpl(CollectionRecordMapper collectionRecordMapper) {
		this.collectionRecordMapper = collectionRecordMapper;
	}
	
	@Override
	public Integer saveCollectionRecord(CollectionRecordDo collectionReordDo) {
		if (Objects.isNull(collectionReordDo) || StringUtils.isAllBlank(collectionReordDo.getFileName(), collectionReordDo.getMongoId())) {
			return 0;
		}
		return collectionRecordMapper.saveCollectionRecord(collectionReordDo);
	}

	@Override
	public List<CollectionRecordVo> findCollectionRecords(Integer pageNumber, Integer pageSize) {
		if (Objects.isNull(pageNumber) || Objects.isNull(pageSize)) {
			pageNumber = 0;
			pageSize = 20;
		}
		return collectionRecordMapper.findCollectionRecords(pageNumber, pageSize);
	}

	@Override
	public Integer countCollectionRecords() {
		return collectionRecordMapper.countCollectionRecords();
	}

}
