package com.gyh.manhattan.common;

/**
 * @author
 */
public class ExecuteResult {

    private String status;
    private String message;
    private String fullErrorMessage;
    private String redirectUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullErrorMessage() {
        return fullErrorMessage;
    }

    public void setFullErrorMessage(String fullErrorMessage) {
        this.fullErrorMessage = fullErrorMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
