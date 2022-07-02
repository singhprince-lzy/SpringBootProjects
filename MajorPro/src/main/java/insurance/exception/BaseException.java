package insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BaseException extends Exception{

	private static final long serialVersionUID = 1L;
	

	public BaseException(String errorMessage) {
		super(errorMessage);
	}
}