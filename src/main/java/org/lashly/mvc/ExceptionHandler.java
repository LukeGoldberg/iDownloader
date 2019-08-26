package org.lashly.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public void handleException(Throwable thrown) {
		log.info("exception message : {}", thrown.getMessage());
		// TODO
	}
	
}
