package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.CollectionService;

@RestController
public class CollectController {
	
	@Autowired
	private Map<String, CollectionService> serviceMap;

	@RequestMapping(value = "/book/collect")
	public void collectBooks() {
		
	}
	
	
}
