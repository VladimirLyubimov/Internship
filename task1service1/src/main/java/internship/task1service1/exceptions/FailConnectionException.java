package internship.task1service1.exceptions;

import internship.task1service1.error_response.ErrorResponse;

public class FailConnectionException extends Exception{
    private final ErrorResponse errorResponse;
    public FailConnectionException(ErrorResponse errorResponse){
        super(errorResponse.getErrorDescriptionMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse(){
        return errorResponse;
    }
}
