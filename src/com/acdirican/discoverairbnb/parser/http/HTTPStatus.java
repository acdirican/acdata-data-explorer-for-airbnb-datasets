package com.acdirican.discoverairbnb.parser.http;

import java.util.List;

import com.acdirican.discoverairbnb.dataset.entities.Property;

/**
 * HTTP status codes and descriptions
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */

public enum HTTPStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    REQUEST_TIMEOUT(408, "Request Timeout");

    private int code;
    private String text;

    HTTPStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }
    public String getText() {
        return text;
    }

    public static HTTPStatus getByCode(int code) {
        return switch(code) {
            case 200 -> OK;
            case 400 -> BAD_REQUEST;
            case 403 -> FORBIDDEN;
            case 404 -> NOT_FOUND;
            case 405 -> METHOD_NOT_ALLOWED;
            case 408 -> REQUEST_TIMEOUT;
            default -> null;
        };
    }
    
    public static HTTPStatus parseFromResponseHeader(String header) {
    	String[] lines = header.split("\n");
    	if (lines.length == 0) {
    		return null;
    	}
    	//Split the first line
    	String parts[] = lines[0].split(" ");
    	if (parts.length < 3) {
    		return null;
    	}
    	
    	if (!parts[0].contains("HTTP")) {
    		return null;
    	}
    	
    	return getByCode(Integer.parseInt(parts[1]));
    }
}
