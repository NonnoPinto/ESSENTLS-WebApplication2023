package com.essentls.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public enum ErrorCode {

    WRONG_FORMAT(-100, HttpServletResponse.SC_BAD_REQUEST,"Wrong format."),
    EMPTY_INPUT_FIELDS(-102, HttpServletResponse.SC_BAD_REQUEST, "One or more input fields are empty."),
    EMAIL_MISSING(-103, HttpServletResponse.SC_BAD_REQUEST, "Email missing"),
    PASSWORD_MISSING(-104, HttpServletResponse.SC_BAD_REQUEST, "Password missing"),
    WRONG_CREDENTIALS(-105, HttpServletResponse.SC_BAD_REQUEST, "Submitted credentials are wrong"),
    WRONG_INTERVALS(-107, HttpServletResponse.SC_CONFLICT, "different passwords"),
    MAIL_ALREADY_USED(-108, HttpServletResponse.SC_CONFLICT, "mail already used"),
    UPDATE_ERROR(-111, HttpServletResponse.SC_BAD_REQUEST, "Problems in updating user"),
    WRONG_REST_FORMAT(-113, HttpServletResponse.SC_BAD_REQUEST, "Wrong rest request format."),
    BADLY_FORMATTED_JSON(-120,  HttpServletResponse.SC_BAD_REQUEST, "The input json is in the wrong format."),
    OPERATION_UNKNOWN(-200, HttpServletResponse.SC_BAD_REQUEST, "Operation unknown."),
    METHOD_NOT_ALLOWED(-500, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "The method is not allowed"),
    TOKEN_TAMPERED(-750, HttpServletResponse.SC_UNAUTHORIZED, "The token has been tampered!!!!"),
    TOKEN_EXPIRED(-751, HttpServletResponse.SC_UNAUTHORIZED, "The token has expired."),
    NOT_VERIFIED(-752, HttpServletResponse.SC_UNAUTHORIZED, "User not verified."),
    INTERNAL_ERROR(-999, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Error");

    private final int errorCode;
    private final int httpCode;
    private final String errorMessage;

    ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHTTPCode() {
        return httpCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public JSONObject toJSON() {
        JSONObject data = new JSONObject();
        data.put("code", errorCode);
        data.put("message", errorMessage);
        JSONObject info = new JSONObject();
        info.put("error", data);
        return info;
    }
}
