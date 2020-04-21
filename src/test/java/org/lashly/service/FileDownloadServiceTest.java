package org.lashly.service;

import java.util.Calendar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.lashly.ApplicationTests;
import org.lashly.domain.dtos.DownloadRecordListDto;
import org.lashly.domain.exceptions.BizException;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDownloadServiceTest extends ApplicationTests {

	@Autowired
	private FileDownloadService fileDownloadService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void listCollectionRecordsTest() {
		DownloadRecordListDto dto = new DownloadRecordListDto();
		fileDownloadService.listCollectionRecords(dto);
	}
	
	@Test
	public void findFileTest() {
		thrown.expect(BizException.class);
		thrown.expectMessage("file not exist");
		fileDownloadService.findFile("", "", Calendar.getInstance().getTime());
	}
	
}
