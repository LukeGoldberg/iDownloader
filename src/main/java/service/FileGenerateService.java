package service;

import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

@Service
public class FileGenerateService {
	
	private ExecutorService s;
	
	public void generateFile(CollectionService dataCollector) {
		// TODO collecte data; generator excel; store into mongodb.
	}

}
