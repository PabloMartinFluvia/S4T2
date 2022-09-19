package cat.ITAcademy.PabloMartin.S4T2.controllers;

import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.BadRequestException;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ConflictException;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ErrorMessage;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.NotFoundException;
import java.net.BindException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,            
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(Exception exception) {
        return new ErrorMessage(exception);
    }
            
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({            
            org.springframework.web.bind.MethodArgumentNotValidException.class,            
    })
    @ResponseBody
    public List<ErrorMessage> notValidBody(MethodArgumentNotValidException exception) {
        List<ErrorMessage> errorList = new LinkedList<>();
        exception.getFieldErrors().forEach(error -> {
            errorList.add(new ErrorMessage(exception, error));
        });
        return errorList;
    } 
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ConflictException.class
    })
    @ResponseBody
    public ErrorMessage conflict(Exception exception) {
        return new ErrorMessage(exception);
    }
    
    
    
    //ERROR MEU ==>> MUST BE SOLVED
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage(exception);
    }        
}
