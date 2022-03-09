package internship.task1service1.exceptions;

import internship.task1service1.error_response.ErrorResponse;

public class ClientSideErrorException extends Exception{
    private final ErrorResponse errorResponse;

    public ClientSideErrorException(ErrorResponse errorResponse){
        super(errorResponse.getErrorDescriptionMessage());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse(){
        return errorResponse;
    }
}
