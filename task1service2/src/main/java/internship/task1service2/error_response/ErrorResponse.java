package internship.task1service2.error_response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponse {
    private String errorName;
    private String errorDescriptionMessage;

    @JsonIgnore
    private int code;

    public ErrorResponse(){}

    public ErrorResponse(String errorName, String errorDescriptionMessage){
        this.errorName = errorName;
        this.errorDescriptionMessage = errorDescriptionMessage;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorDescriptionMessage() {
        return errorDescriptionMessage;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public void setErrorDescriptionMessage(String errorDescriptionMessage) {
        this.errorDescriptionMessage = errorDescriptionMessage;
    }
}

