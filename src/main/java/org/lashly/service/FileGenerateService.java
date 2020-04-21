package org.lashly.service;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.Const;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.dtos.BaseDto;
import org.lashly.domain.dtos.SearchResultDto;
import org.lashly.domain.enums.CollectionEnum;
import org.lashly.domain.enums.CollectionStateEnum;
import org.lashly.service.helper.ExcelHelper;
import org.lashly.service.helper.MongoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.gridfs.GridFSUploadStream;

import lombok.extern.slf4j.Slf4j;

/**
 * file generate service
 */
@Service
@Slf4j
public class FileGenerateService {
	
	private static final int POLL_SIZE = 5;
	
	@Autowired
	private Map<String, CollectionService> serviceMap;
	
	@Autowired
	private MongoHelper mongoHelper;
	
	@Autowired
	private ExcelHelper excelHelper;
	
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
			GridFSUploadStream outputStream = mongoHelper.getUploadStream(resultDto.getFileName());
			try {
				excelHelper.generateExcel(resultDto.getData(), outputStream, 
						resultDto.getSheetName(), resultDto.getHeaders(), Const.YEAR_MONTH_DAY_PATTERN);
			} catch (Exception e) {
				saveCollectionRecord(collector, null, CollectionStateEnum.FAILED.toString());
				log.error("generate excel failed, reason: {}", e.getCause());
				e.printStackTrace();
			}
			String objectId = outputStream.getObjectId().toHexString();
			outputStream.close();
			saveCollectionRecord(collector, objectId, CollectionStateEnum.SUCCESS.toString());
		});
	}

	/**
	 * save collection record.
	 * 
	 * @param collector collector
	 * @param objectId ObjectID
	 * @param state collection state
	 */
	private void saveCollectionRecord(CollectionEnum collector, String objectId, String state) {
		Calendar calendar = Calendar.getInstance();
		CollectionRecordDo recordDo = new CollectionRecordDo();
		recordDo.setMongoId(objectId);
		recordDo.setState(state);
		recordDo.setFileName(collector.toString() +
				"-" +
		        calendar.get(Calendar.MONTH) +
		        "-" +
		        calendar.get(Calendar.HOUR_OF_DAY));
		collectionRecordDao.saveCollectionRecord(recordDo);
	}
	
}
