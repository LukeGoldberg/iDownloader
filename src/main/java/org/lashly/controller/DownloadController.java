package org.lashly.controller;

import static org.springframework.http.HttpHeaders.IF_MODIFIED_SINCE;
import static org.springframework.http.HttpHeaders.IF_NONE_MATCH;

import java.util.Date;
import java.util.Optional;

import org.lashly.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	@Autowired
	private FileDownloadService fileDownloadService;
	
	@RequestMapping("list")
	public ResponseEntity<Resource> downloadList() {
		return null;
	}
	
	@RequestMapping(value = "{fileId}")
	public ResponseEntity<Resource> download(
			@PathVariable("fileId") String fileId,
			@RequestHeader(IF_NONE_MATCH) Optional<String> requestEtagOpt,
			@RequestHeader(IF_MODIFIED_SINCE) Optional<Date> ifModifiedSinceOpt) {
		return fileDownloadService.findFile(fileId)
				.get()
				.getFile(requestEtagOpt, ifModifiedSinceOpt);
	}

}
