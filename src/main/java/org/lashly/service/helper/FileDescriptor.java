package org.lashly.service.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.lashly.domain.ThrottlingInputStream;
import org.lashly.domain.exceptions.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mongodb.client.gridfs.model.GridFSFile;

@Component
public class FileDescriptor {
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	private String fileId;
	private GridFSFile gridFSFile;
	
	/**
	 * initial this class
	 * 
	 * @param fileId file's MongoDB ID 
	 * @return file descriptor
	 */
	public Optional<FileDescriptor> init(String fileId) {
		this.fileId = fileId;
		this.gridFSFile =
				gridFsTemplate.findOne(
						new Query()
						.addCriteria(Criteria.where("id").is(fileId))
						);
		return Optional.of(this);
	}
	
	/**
	 * get file's inputstream
	 * 
	 * @param requestEtagOpt ETag header
	 * @param ifModifiedSinceOpt if modifid since header
	 * @return inputstream
	 */
	public ResponseEntity<Resource> getFile(Optional<String> requestEtagOpt, Optional<Date> ifModifiedSinceOpt) {
		if (checkCache(requestEtagOpt, ifModifiedSinceOpt)) {
			return getResponse(HttpStatus.NOT_MODIFIED, null);
		}
		return getResponse(HttpStatus.OK, getFileResource());
	}
	
	/**
	 * check if file'content has been modified
	 * 
	 * @param requestEtagOpt ETag header
	 * @param ifModifiedSinceOpt if modified since header
	 * @return true or not
	 */
	private boolean checkCache(Optional<String> requestEtagOpt, Optional<Date> ifModifiedSinceOpt) {
		if (gridFSFile == null) {
			return false;
		}
		boolean etagEquals = requestEtagOpt
				.map(val -> StringUtils.equals(val, gridFSFile.getMD5()))
				.orElse(false);
		boolean ifModifiedEquals = ifModifiedSinceOpt
				.map(val -> val.before(gridFSFile.getUploadDate()))
				.orElse(false);
		return etagEquals || ifModifiedEquals;
	}
	
	/**
	 * get file's inputstream
	 * 
	 * @return inputstream
	 */
	private InputStreamResource getFileResource() {
		InputStream inputStream;
		try {
			inputStream = gridFsTemplate.getResource(gridFSFile).getInputStream();
		} catch (IllegalStateException | IOException e) {
			throw new BizException("can not download now, try again later");
		}
		ThrottlingInputStream throttlingInputStream = new ThrottlingInputStream(inputStream);
		return new InputStreamResource(throttlingInputStream);
	}
	
	/**
	 * generate one <code>ResponseEntity</code>
	 * 
	 * @param status HttpStatus
	 * @param body body
	 * @return ResponseEntity
	 */
	private ResponseEntity<Resource> getResponse(HttpStatus status, Resource body) {
		final ResponseEntity.BodyBuilder responseBuilder = ResponseEntity
				.status(status)
				.eTag(gridFSFile.getMD5())
				.contentLength(gridFSFile.getLength())
				.contentType(MediaType.valueOf(gridFSFile.getContentType()))
				.lastModified(gridFSFile.getUploadDate().getTime());
		return responseBuilder.body(body);
	}

}
