package org.lashly.service.helper;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import org.lashly.domain.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

@Component
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
	@SuppressWarnings("unchecked")
	public String generateExcel(SearchResultDto dto) {
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        GridFSUploadStream gridFSUploadStream = gridFSBucket.openUploadStream(dto.getFileName());
        exportExcelUtil.exportExcel(dto.getSheetName(), dto.getHeaders(), dto.getData(), gridFSUploadStream);
        String objectId = gridFSUploadStream.getObjectId().toHexString();
        gridFSUploadStream.close();
		return objectId;
	}
	
}
