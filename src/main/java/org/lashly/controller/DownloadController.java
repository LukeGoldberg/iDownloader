package org.lashly.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	@RequestMapping("list")
	public ResponseEntity<Resource> downloadList() {
		return null;
	}
	
	@RequestMapping(value = "{fileId}")
	public ResponseEntity<Resource> download(@PathVariable("fileId") String fileId) {
		return null;
	}

}
