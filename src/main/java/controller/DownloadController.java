package controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
	
	@RequestMapping(value = "/download/{fileId}")
	public ResponseEntity<Resource> download(@PathVariable("fileId") String fileId) {
		return null;
	}

}
