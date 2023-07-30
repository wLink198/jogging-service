package com.svc.jogging.api.util;

import com.svc.jogging.model.exception.BusinessException;
import com.svc.jogging.model.res.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestHelper {

	public static <T> ResponseEntity<T> createResponse(ResponseData res) {
		if (res.isOk()) {
			return ResponseEntity.status(HttpStatus.OK).body((T) res);
		}

		if (res.getException() instanceof BusinessException) {
			res.setError(new ResponseData.Error(HttpStatus.BAD_REQUEST.value(), res.getException().getMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((T) res);
		}

		res.setError(new ResponseData.Error("The system encountered an unknown error. Contact admin for more information."));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) res);
	}
}
