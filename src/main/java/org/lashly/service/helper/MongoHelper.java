package org.lashly.service.helper;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.lashly.domain.dto.SearchResultDto;
import org.lashly.domain.exceptions.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MongoHelper {

    private GridFSBucket gridFSBucket;

    @Autowired
    public MongoHelper(MongoDbFactory mongoDbFactory) {
        this.gridFSBucket = GridFSBuckets.create(mongoDbFactory.getDb());
    }

	/**
	 * generate excel
	 * 
	 * @param dto dto
	 * @return MongoDB ID
	 */
	public String generateFile(SearchResultDto dto) {
        String objectId = StringUtils.EMPTY;
        try {
			FileInputStream in = new FileInputStream(ResourceUtils.getFile("classpath:file.txt"));
			GridFSUploadStream gridFSUploadStream = gridFSBucket.openUploadStream(dto.getFileName());
			byte[] buf = new byte[512];
			while (in.read(buf) != -1) {
				gridFSUploadStream.write(buf);
				gridFSUploadStream.flush();
			}
			objectId = gridFSUploadStream.getObjectId().toHexString();
			gridFSUploadStream.close();
			in.close();
		} catch (IOException e) {
			log.error(e.getCause() + " : " + e.getMessage());
			e.printStackTrace();
			throw new BizException("collect data failed");
		}
		return objectId;
	}
	
}
