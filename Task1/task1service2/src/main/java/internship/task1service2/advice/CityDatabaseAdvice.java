package internship.task1service2.advice;

import internship.task1service2.error_response.ErrorResponse;
import internship.task1service2.exceptions.DatabaseConnectionException;
import internship.task1service2.exceptions.SQLRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityDatabaseAdvice {
    @ExceptionHandler(SQLRequestException.class)
    public ResponseEntity<ErrorResponse> sqlRequestExceptionHandler(SQLRequestException e){
        ErrorResponse response = new ErrorResponse("Inappropriate SQL request", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<ErrorResponse> databaseConnectionExceptionHandler(DatabaseConnectionException e){
        ErrorResponse response = new ErrorResponse("Database connection fail", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
