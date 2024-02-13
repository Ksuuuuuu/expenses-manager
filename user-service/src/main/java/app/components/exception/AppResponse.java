package app.components.exception;

import lombok.Data;

@Data
public class AppResponse {
    private Object data;
    private int statusCode;
    private String message;

    public AppResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public AppResponse(Object data, int statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }
}
