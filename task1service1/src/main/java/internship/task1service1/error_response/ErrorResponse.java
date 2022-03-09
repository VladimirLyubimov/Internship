package internship.task1service1.error_response;

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

    public ErrorResponse(String errorName, String errorDescriptionMessage, int code){
        this.errorName = errorName;
        this.errorDescriptionMessage = errorDescriptionMessage;
        this.code = code;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorDescriptionMessage() {
        return errorDescriptionMessage;
    }

    public int getCode() {
        return code;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public void setErrorDescriptionMessage(String errorDescriptionMessage) {
        this.errorDescriptionMessage = errorDescriptionMessage;
    }
}
