package internship.task1service1.advices;

import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.exceptions.DatabaseConnectionException;
import internship.task1service1.exceptions.EmptyResultException;
import internship.task1service1.exceptions.FailConnectionException;
import internship.task1service1.exceptions.SQLRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityAdvice {

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<ErrorResponse> emptyResultExceptionHandler(EmptyResultException e){
        return new ResponseEntity<>(new ErrorResponse("Empty result", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FailConnectionException.class)
    public ResponseEntity<ErrorResponse> connectionExceptionHandler(FailConnectionException e){
        return new ResponseEntity<>(new ErrorResponse("Connection fail", e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(SQLRequestException.class)
    public ResponseEntity<ErrorResponse> sqlRequestException(SQLRequestException e){
        return new ResponseEntity<>(new ErrorResponse("Error with SQL request", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<ErrorResponse> databaseConnectionExceptionHandler(DatabaseConnectionException e){
        return new ResponseEntity<>(new ErrorResponse("Database connection fail", e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
