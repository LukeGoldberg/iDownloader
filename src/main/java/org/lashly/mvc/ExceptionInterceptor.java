package org.lashly.mvc;

import org.lashly.domain.RespResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionInterceptor {

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public RespResult handleException(Throwable thrown) {
		log.info("exception message : {}", thrown.getMessage());
		if (thrown instanceof RuntimeException) {
            RespResult result = new RespResult();
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("system error");
            return result;
        }
		RespResult result = new RespResult();
		result.setCode(HttpStatus.OK.value());
		result.setMessage(thrown.getMessage());
		return result;
	}
	
}
