package org.lashly.service;

import java.util.Date;
import java.util.List;

import org.lashly.dao.CollectionRecordDao;
import org.lashly.domain.dos.CollectionRecordDo;
import org.lashly.domain.dto.DownloadRecordListDto;
import org.lashly.domain.exceptions.BizException;
import org.lashly.service.helper.FileDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
		FileDescriptor fileDesc = fileDescriptor.init(fileId);
		if (!fileDesc.initialed()) {
			throw new BizException("file not exist");
		}
		return fileDesc.getFile(requestETagOpt, ifModifiedSinceOpt);
	}

    /**
     * list collection record
     *
     * @param dto download record list dto
     * * @return collection record list
     */
	public List<CollectionRecordDo> listCollectionRecords(DownloadRecordListDto dto) {
        return collectionRecordDao.findCollectionRecords(dto.getPageNumber(), dto.getPageSize());
    }

}
