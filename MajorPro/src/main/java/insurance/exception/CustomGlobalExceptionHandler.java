package insurance.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		FieldError fieldError= ex.getBindingResult().getFieldError();
		String errorDetailsMsg = fieldError.getField() + " " + fieldError.getDefaultMessage();
		CustomErrorDetails errorDetails = new CustomErrorDetails(
				new Date(),"Validation Error" ,errorDetailsMsg);
		return ResponseEntity.badRequest().body(errorDetails);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorDetailsMsg = ex.getCause().getMessage();
		errorDetailsMsg = errorDetailsMsg.substring(0, errorDetailsMsg.indexOf("\"", errorDetailsMsg.indexOf("\"") + 1));
		CustomErrorDetails errorDetails = new CustomErrorDetails(
				new Date(),"Invalid Parameter" ,errorDetailsMsg);
		return ResponseEntity.badRequest().body(errorDetails);
	}

	@ExceptionHandler({NotFoundException.class, NullPointerException.class, UsernameNotFoundException.class})
	public ResponseEntity<Object> customNotFoundException(
			Exception ex,
			WebRequest request){
		
		CustomErrorDetails errorDetails = new CustomErrorDetails(
				new Date(), ex.getMessage() ,request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({BaseException.class})
	public ResponseEntity<Object> customNotFoundException(
			BaseException ex,
			WebRequest request){
		
		CustomErrorDetails errorDetails = new CustomErrorDetails(
				new Date(), ex.getMessage() ,request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}