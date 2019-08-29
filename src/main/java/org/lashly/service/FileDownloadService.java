package org.lashly.service;

import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.exceptions.BizException;
import org.lashly.service.helper.FileDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileDownloadService {
	
	private FileDescriptor fileDescriptor;

	private CollectionRecordDao collectionRecordDao;

	@Autowired
	public FileDownloadService(FileDescriptor fileDescriptor, CollectionRecordDao collectionRecordDao) {
		this.fileDescriptor = fileDescriptor;
		this.collectionRecordDao = collectionRecordDao;
	}

	/**
	 * find file
	 * 
	 * @param fileId file's MongoDB ID
	 * @return file descriptor
	 */
	public ResponseEntity<Resource> findFile(String fileId, String requestETagOpt, Date ifModifiedSinceOpt) {
		Optional<FileDescriptor> fileDescriptorOpt = fileDescriptor.init(fileId);
		if (!fileDescriptorOpt.isPresent()) {
			throw new BizException("file not exist");
		}
		return fileDescriptorOpt.get().getFile(requestETagOpt, ifModifiedSinceOpt);
	}

    /**
     * list collection record
     *
     * @param pageNumber page number
     * @param pageSize page size
     * @return collection record list
     */
	public List<CollectionRecordDo> listCollectionRecords(Integer pageNumber, Integer pageSize) {
        return collectionRecordDao.findCollectionRecords(pageNumber, pageSize);
    }

}
