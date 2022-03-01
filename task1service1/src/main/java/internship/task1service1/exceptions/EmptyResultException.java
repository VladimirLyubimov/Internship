package internship.task1service1.exceptions;

import internship.task1service1.error_response.ErrorResponse;

public class EmptyResultException extends Exception{
    public EmptyResultException(String message){
        super(message);
    }
}
