package org.lashly.service;

import java.util.Optional;

import org.lashly.service.helper.FileDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {
	
	@Autowired
	private FileDescriptor fileDescriptor;	
	
	/**
	 * find file
	 * 
	 * @param fileId file's MongoDB ID
	 * @return file descriptor
	 */
	public Optional<FileDescriptor> findFile(String fileId) {
		return fileDescriptor.init(fileId);
	}

}
