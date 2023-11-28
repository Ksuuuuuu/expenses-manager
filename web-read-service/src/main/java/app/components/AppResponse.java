package app.components;

import lombok.Data;

@Data
public class AppResponse {
    private int statusCode;
    private String message;

    public AppResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
