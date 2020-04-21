package org.lashly.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;

@Component
public class MongoHelper {

    private GridFSBucket gridFSBucket;

    @Autowired
    public MongoHelper(MongoDbFactory mongoDbFactory) {
        this.gridFSBucket = GridFSBuckets.create(mongoDbFactory.getDb());
    }
    
    public GridFSUploadStream getUploadStream(String fileName) {
    	return gridFSBucket.openUploadStream(fileName);
    }

}
