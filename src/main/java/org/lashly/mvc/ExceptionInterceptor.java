package org.lashly.mvc;

import org.lashly.domain.RespResult;
import org.lashly.domain.exceptions.BizException;
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
	    if (thrown instanceof BizException) {
	        BizException e = (BizException) thrown;
            log.info("exception message : {}", e.getInfo());
            RespResult result = new RespResult();
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage(e.getInfo());
            return result;
        }
		log.info("exception message : {}", thrown.getMessage());
        RespResult result = new RespResult();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage("system error");
		return result;
	}
	
}
