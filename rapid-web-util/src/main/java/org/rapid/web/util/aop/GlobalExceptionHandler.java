package org.rapid.web.util.aop;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.rapid.core.bean.exception.BizException;
import org.rapid.core.bean.exception.SdkException;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.code.ICode;
import org.rapid.core.bean.model.message.Response;
import org.rapid.core.bean.model.option.Option;
import org.rapid.util.exception.RapidException;
import org.rapid.util.exception.RapidRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Response<Void> handler(Exception e) {
		Response<Void> response = new Response<Void>(Code.SYS_ERROR);
		if (e instanceof IllegalArgumentException) {
			logger.warn("Controller 方法参数绑定失败，请注意是否使用 @RequestParam！", e);
			return Option.handleResponse(response);
		} else if (e instanceof DuplicateKeyException) {
			logger.info("主键异常冲突：", e.getCause());
			return Option.createResponse(Code.KEY_DUPLICATED);
		} else {
			response.setDesc(e.getMessage());
			logger.warn("系统错误！", e);
			return Option.handleResponse(response);
		}
	}

	@ResponseBody
	@ExceptionHandler({ RapidException.class, RapidRuntimeException.class})
	public Response<Void> bizExceptionHandler(Exception e) {
		if (e instanceof SdkException) {
			BizException bizException = (BizException) e;
			ICode code = bizException.getCode();
			Response<Void> response = Option.createResponse(code);
			response.setDesc(((SdkException) e).getErrorMessage());
			return response;
		}
		if (e instanceof BizException) {
			BizException bizException = (BizException) e;
			ICode code = bizException.getCode();
			return Option.createResponse(code);
		}
		return Option.createResponse(Code.SYS_ERROR);
	}

	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response<Void> httpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException ex) {
		return Option.createResponse(Code.UNSUPPORT_HTTP_METHOD);
	}

	@ResponseBody
	@ExceptionHandler({ HttpMessageNotReadableException.class, MissingServletRequestParameterException.class })
	public Response<Void> httpMessageNotReadableException(Exception ex) {
		Response<Void> result = Option.createResponse(Code.PARAM_ERROR);
		result.setDesc(ex.toString());
		return result;
	}

	@ResponseBody
	@ExceptionHandler(BindException.class)
	public Response<Void> bindExceptionHandler(BindException ex) {
		return _validatorError(ex.getBindingResult());
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public Response<Void> constraintViolationExceptionHandler(ConstraintViolationException ex) {
		Response<Void> result = new Response<Void>(Code.PARAM_ERROR);
		Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
		StringBuilder reason = new StringBuilder("[");
		for (ConstraintViolation<?> constraintViolation : set)
			reason.append(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName()).append("-")
					.append(constraintViolation.getMessage()).append(";");
		reason.deleteCharAt(reason.length() - 1);
		reason.append("]");
		result.setDesc(result.getDesc() + "[" + reason.toString() + "]");
		return result;
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return _validatorError(ex.getBindingResult());
	}

	/**
	 * 上传文件超过上限
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public Response<Void> uploadSizeExceededHandler(MaxUploadSizeExceededException ex) {
		Response<Void> response = Option.createResponse(Code.UPLOAD_SIZE_EXCEEDED);
		response.setDesc(String.valueOf(ex.getMaxUploadSize()));
		return response;
	}

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Response<Void> unsupportedMediaTypeHandler(HttpMediaTypeNotSupportedException ex) {
		Response<Void> response = Option.createResponse(Code.UNSUPPORT_CONTENT_TYPE);
		response.setDesc(ex.getContentType().toString());
		return response;
	}

	private Response<Void> _validatorError(BindingResult bindingResult) {
		List<FieldError> errors = bindingResult.getFieldErrors();
		StringBuilder reason = new StringBuilder("[");
		for (FieldError error : errors)
			reason.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
		reason.deleteCharAt(reason.length() - 1);
		reason.append("]");
		Response<Void> response = Option.createResponse(Code.PARAM_ERROR);
		response.setDesc(reason.toString());
		return response;
	}
}
