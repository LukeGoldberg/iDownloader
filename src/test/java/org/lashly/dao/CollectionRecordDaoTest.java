package org.lashly.dao;

import org.junit.Test;
import org.lashly.ApplicationTests;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.enums.CollectionStateEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectionRecordDaoTest extends ApplicationTests {
	
	@Autowired
	private CollectionRecordDao collectionRecordDao;
	
	@Test
	public void saveCollectionRecordTest() {
		CollectionRecordDo collectionRecordDo = new CollectionRecordDo();
		collectionRecordDo.setFileName("fileName");
		collectionRecordDo.setMongoId("_id123");
		collectionRecordDo.setState(CollectionStateEnum.SUCCESS.toString());
		collectionRecordDao.saveCollectionRecord(collectionRecordDo);
	}
	
	@Test
	public void countCollectionRecordsTest() {
		collectionRecordDao.countCollectionRecords();
	}
	
	@Test
	public void findCollectionRecordsTest() {
		collectionRecordDao.findCollectionRecords(null, null);
	}

}
