package internship.task1service1.advices;

import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityAdvice {

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<ErrorResponse> emptyResultExceptionHandler(EmptyResultException e){
        return new ResponseEntity<>(new ErrorResponse("City isn't found", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FailConnectionException.class)
    public ResponseEntity<ErrorResponse> connectionExceptionHandler(FailConnectionException e){
        return new ResponseEntity<>(new ErrorResponse("Connection fail", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
