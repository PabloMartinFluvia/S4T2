package cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ErrorMessage {
    
    private final String error;

    private final String message;

    public ErrorMessage(Exception exception) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();               
    }
    
    public ErrorMessage(MethodArgumentNotValidException exception, FieldError fieldError) {
        this.error = exception.getClass().getSimpleName();
        this.message = fieldError.getField()+"-> "+fieldError.getDefaultMessage();        
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
