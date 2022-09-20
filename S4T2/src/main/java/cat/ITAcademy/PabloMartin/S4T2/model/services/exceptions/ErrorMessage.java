package cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@ToString
public class ErrorMessage {
    
    private final String error;

    private final String message;

    private final Integer code;
    
    public ErrorMessage(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage(); 
        this.code = code;
    }
    
    public ErrorMessage(MethodArgumentNotValidException exception, FieldError fieldError, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = fieldError.getField()+"-> "+fieldError.getDefaultMessage();     
        this.code = code;
    }
}
