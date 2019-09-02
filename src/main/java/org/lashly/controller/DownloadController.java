package org.lashly.controller;

import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.dto.DownloadRecordListDto;
import org.lashly.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.IF_MODIFIED_SINCE;
import static org.springframework.http.HttpHeaders.IF_NONE_MATCH;

/**
 * download request
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
	
	@Autowired
	private FileDownloadService fileDownloadService;

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CollectionRecordDo> downloadList(DownloadRecordListDto dto) {
        return fileDownloadService.listCollectionRecords(dto);
	}
	
	@RequestMapping(value = "/file/{fileId}", method = {RequestMethod.POST, RequestMethod.HEAD}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Resource> download(
			@PathVariable("fileId") String fileId,
			@RequestHeader(IF_NONE_MATCH) String requestETagOpt,
			@RequestHeader(IF_MODIFIED_SINCE) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date ifModifiedSinceOpt) {
		return fileDownloadService.findFile(fileId, requestETagOpt, ifModifiedSinceOpt);
	}

}
