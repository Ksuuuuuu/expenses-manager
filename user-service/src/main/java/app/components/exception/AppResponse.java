package app.components.exception;

import lombok.Data;

@Data
public class AppResponse {
    private Long data;
    private int statusCode;
    private String message;

    public AppResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public AppResponse(Long data, int statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }
}
