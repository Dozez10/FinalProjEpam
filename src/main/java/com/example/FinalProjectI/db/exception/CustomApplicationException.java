package com.example.FinalProjectI.db.exception;

public class CustomApplicationException extends Exception{

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public CustomApplicationException(String message) {
        super(message);
        this.errorCode = 0;
    }
    public CustomApplicationException(String ownMessage , String message) {
        super(message.concat("\n\t\tOwn message is ").concat(ownMessage));
        this.errorCode = 0;
    }
    public CustomApplicationException(String ownMessage , String message , Throwable cause) {
        super(message.concat("\n\t\tOwn message is ").concat(ownMessage),cause);
        errorCode =0;

    }
    public CustomApplicationException(String message,int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public CustomApplicationException(String message , Throwable cause,int errorCode) {
        super(message,cause);
        this.errorCode = errorCode;

    }
    public CustomApplicationException(String ownMessage , String message,int errorCode) {
        super(message.concat("\n\t\tOwn message is ").concat(ownMessage));
        this.errorCode = errorCode;
    }
    public CustomApplicationException(String ownMessage , String message , Throwable cause,int errorCode) {
        super(message.concat("\n\t\tOwn message is ").concat(ownMessage),cause);
        this.errorCode = errorCode;
    }
}
