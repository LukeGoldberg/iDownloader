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
	
	private GridFsTemplate gridFsTemplate;

	private GridFSFile gridFSFile;

	private boolean initialed;

	@Autowired
    public FileDescriptor(GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
    }

	/**
	 * initial this class
	 * 
	 * @param objectId MongoDB ObjectID
	 * @return file descriptor
	 */
	public FileDescriptor init(String objectId) {
		this.gridFSFile =
				gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(objectId)));
		initialed = (gridFSFile != null);
		return this;
	}

	public boolean initialed() {
	    return initialed;
    }

	/**
	 * get file's inputstream
	 * 
	 * @param requestETagOpt ETag header
	 * @param ifModifiedSinceOpt if modifid since header
	 * @return inputstream
	 */
	public ResponseEntity<Resource> getFile(String requestETagOpt, Date ifModifiedSinceOpt) {
		if (checkCache(requestETagOpt, ifModifiedSinceOpt)) {
			return generateResponse(HttpStatus.NOT_MODIFIED, null);
		}
		return generateResponse(HttpStatus.OK, getFileResource());
	}
	
	/**
	 * check if file'content has been modified
	 * 
	 * @param requestETagOpt ETag header
	 * @param ifModifiedSinceOpt if modified since header
	 * @return true or not
	 */
	private boolean checkCache(String requestETagOpt, Date ifModifiedSinceOpt) {
		if (!initialed) {
			return false;
		}
		boolean eTagEquals = false;
		if (StringUtils.isNotBlank(requestETagOpt)) {
		    eTagEquals = Optional.of(requestETagOpt)
                    .map(val -> StringUtils.equals(val, gridFSFile.getMD5()))
                    .orElse(false);
        }
		boolean ifModifiedEquals = false;
		if (ifModifiedSinceOpt != null) {
		    ifModifiedEquals = Optional.of(ifModifiedSinceOpt)
                    .map(val -> val.before(gridFSFile.getUploadDate()))
                    .orElse(false);
        }
		return eTagEquals || ifModifiedEquals;
	}
	
	/**
	 * get file's input stream
	 * 
	 * @return input javastream
	 */
	private InputStreamResource getFileResource() {
		InputStream inputStream;
		try {
			inputStream = gridFsTemplate.getResource(gridFSFile).getInputStream();
		} catch (IllegalStateException | IOException e) {
			throw new BizException("can not download now, try again later");
		}
		return new InputStreamResource(new ThrottlingInputStream(inputStream));
	}
	
	/**
	 * generate one <code>ResponseEntity</code>
	 * 
	 * @param status HttpStatus
	 * @param body body
	 * @return ResponseEntity
	 */
	private ResponseEntity<Resource> generateResponse(HttpStatus status, Resource body) {
		final ResponseEntity.BodyBuilder responseBuilder = ResponseEntity
				.status(status)
				.eTag(gridFSFile.getMD5())
				.contentLength(gridFSFile.getLength())
				.contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.lastModified(gridFSFile.getUploadDate().getTime());
        return responseBuilder.body(body);
	}

}
