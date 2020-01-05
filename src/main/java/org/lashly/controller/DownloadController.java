package org.lashly.controller;

import static org.springframework.http.HttpHeaders.IF_MODIFIED_SINCE;
import static org.springframework.http.HttpHeaders.IF_NONE_MATCH;

import java.util.Date;

import org.lashly.domain.RespResult;
import org.lashly.domain.dto.DownloadRecordListDto;
import org.lashly.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * download request
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
	
	private FileDownloadService fileDownloadService;

	@Autowired
	public DownloadController(FileDownloadService fileDownloadService) {
		this.fileDownloadService = fileDownloadService;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public RespResult downloadList(DownloadRecordListDto dto) {
        return RespResult.sendData(fileDownloadService.listCollectionRecords(dto));
	}
	
	@RequestMapping(value = "/file/{fileId}", method = {RequestMethod.GET, RequestMethod.HEAD}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Resource> download(
			@PathVariable("fileId") String fileId,
			@RequestHeader(name = IF_NONE_MATCH, required = false) String requestETagOpt,
			@RequestHeader(name = IF_MODIFIED_SINCE, required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date ifModifiedSinceOpt) {
		return fileDownloadService.findFile(fileId, requestETagOpt, ifModifiedSinceOpt);
	}

}
