package app.components.exception;

public class RecordAlreadyExistException extends Exception{
    public RecordAlreadyExistException(String message){
        super(message);
    }
}
