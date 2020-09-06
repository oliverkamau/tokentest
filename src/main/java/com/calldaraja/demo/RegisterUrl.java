package com.calldaraja.demo;

public class RegisterUrl {

    private String ShortCode;

    private String ResponseType;

    private String ConfirmationURL;

    private String ValidationURL;

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getResponseType() {
        return ResponseType;
    }

    public void setResponseType(String responseType) {
        ResponseType = responseType;
    }

    public String getConfirmationURL() {
        return ConfirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        ConfirmationURL = confirmationURL;
    }

    public String getValidationURL() {
        return ValidationURL;
    }

    public void setValidationURL(String validationURL) {
        ValidationURL = validationURL;
    }
}
