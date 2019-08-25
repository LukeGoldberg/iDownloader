package org.lashly.service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.bo.CollectionRecordBo;
import org.lashly.domain.dto.BaseDto;
import org.lashly.domain.dto.SearchResultDto;
import org.lashly.domain.enums.CollectionEnum;
import org.lashly.service.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileGenerateService {
	
	private static final int POLL_SIZE = 5;
	
	@Autowired
	private Map<String, CollectionService> serviceMap;
	
	@Autowired
	private ExcelHelper excelHelper;
	
	@Autowired
	private CollectionRecordDao collectionRecordDao;
	
	private static ExecutorService executor = 
			Executors.newFixedThreadPool(POLL_SIZE);
	
	public void generateFile(CollectionEnum collector, BaseDto dto) {
		executor.execute(() -> {
			// 1. collect data;
			CollectionService collectionService = 
					serviceMap.get(collector.getServiceName());
			SearchResultDto resultDto = collectionService.collect(dto);
			// 2. generator excel;
			excelHelper.generateExcel(resultDto);
			// 3. generate collection record.
			CollectionRecordBo recordBo = new CollectionRecordBo();
			collectionRecordDao.saveCollectionRecord(recordBo);
		});
	}
	
}
