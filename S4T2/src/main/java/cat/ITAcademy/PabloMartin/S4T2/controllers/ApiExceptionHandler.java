package cat.ITAcademy.PabloMartin.S4T2.controllers;

import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.BadRequestException;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ConflictException;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.ErrorMessage;
import cat.ITAcademy.PabloMartin.S4T2.model.services.exceptions.NotFoundException;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {   
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,                        
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.bind.MissingPathVariableException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.web.server.ServerWebInputException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.BAD_REQUEST.value());
    }
            
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({            
            org.springframework.web.bind.MethodArgumentNotValidException.class,            
    })
    @ResponseBody
    public List<ErrorMessage> unsuccessfulValidation(MethodArgumentNotValidException exception) {
        List<ErrorMessage> errorList = new LinkedList<>();
        exception.getFieldErrors().forEach(error -> {
            errorList.add(new ErrorMessage(exception, error, HttpStatus.BAD_REQUEST.value()));
        });
        return errorList;
    } 
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ConflictException.class
    })
    @ResponseBody
    public ErrorMessage conflict(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.CONFLICT.value());
    }        
    
    //ERROR MEU ==>> MUST BE SOLVED
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage fatalUnexpectedException(Exception exception) {
        exception.printStackTrace(); //només en la fase de dessarollo
        return new ErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }        
}
