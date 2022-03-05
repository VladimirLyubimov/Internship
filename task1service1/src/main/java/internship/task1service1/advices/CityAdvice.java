package internship.task1service1.advices;

import internship.task1service1.error_response.ErrorResponse;
import internship.task1service1.exceptions.*;
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

    @ExceptionHandler(ObviouslyIncorrectInputDataException.class)
    public ResponseEntity<ErrorResponse> obviouslyIncorrectInputDataExceptionHandler(ObviouslyIncorrectInputDataException e){
        return new ResponseEntity<>(new ErrorResponse("Incorrect input data", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> numberFormatExceptionHandler(NumberFormatException e){
        String errMessage = e.getMessage();
        String wrongNum = new StringBuilder().append(errMessage.substring(errMessage.indexOf("\"")+1, errMessage.lastIndexOf("\""))).toString();
        return new ResponseEntity<>(new ErrorResponse("Incorrect number format", "A decimal integer for city id or city amount expected, but got: " + wrongNum), HttpStatus.BAD_REQUEST);
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
