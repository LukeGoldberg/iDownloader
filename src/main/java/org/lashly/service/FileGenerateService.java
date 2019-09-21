package org.lashly.service;

import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.dto.BaseDto;
import org.lashly.domain.dto.SearchResultDto;
import org.lashly.domain.enums.CollectionEnum;
import org.lashly.service.helper.MongoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileGenerateService {
	
	private static final int POLL_SIZE = 5;
	
	@Autowired
	private Map<String, CollectionService> serviceMap;
	
	@Autowired
	private MongoHelper excelHelper;
	
	@Autowired
	private CollectionRecordDao collectionRecordDao;
	
	private static ExecutorService executor = 
			Executors.newFixedThreadPool(POLL_SIZE);
	
	/**
	 * 1. collection data using <code>CollectionService</code>;
	 * 2. store into MongoDB;
	 * 3. generate one record for download page.
	 * 
	 * @param collector <code>CollectionService</code>
	 * @param dto dto
	 */
	public void generateFile(CollectionEnum collector, BaseDto dto) {
		executor.execute(() -> {
			CollectionService collectionService =
					serviceMap.get(collector.getServiceName());
			SearchResultDto resultDto = collectionService.collect(dto);
			String objecId = excelHelper.generateExcel(resultDto);
			Calendar calendar = Calendar.getInstance();
			CollectionRecordDo recordBo = new CollectionRecordDo();
			recordBo.setMongoId(objecId);
			recordBo.setFileName(collector.toString() +
                    calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.HOUR_OF_DAY));
			collectionRecordDao.saveCollectionRecord(recordBo);
		});
	}
	
}
