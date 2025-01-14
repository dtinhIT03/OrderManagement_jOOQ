package org.example.ordermanagement_jooq.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentTypeMismatchException.class,ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerException(Exception e, WebRequest request){
        System.out.println("handle Validation Exception");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));

        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        String message =e.getMessage();
        if(e instanceof MethodArgumentTypeMismatchException){
            errorResponse.setMessage("Failed to convert value of type");

        }else if(e instanceof ConstraintViolationException){
            message=message.substring(message.indexOf(" ")+1);
            errorResponse.setError("Parameter invalid");
            errorResponse.setMessage(message);
        }

        return errorResponse;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMessageNotReadableException(Exception e, WebRequest request){
        System.out.println("handle MessageNotReadable");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));

        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        if(e instanceof HttpMessageNotReadableException){
            errorResponse.setMessage(e.getMessage());

        }

        return errorResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(Exception e, WebRequest request){
        System.out.println("handle MethodArgumentNotValidException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        String message =e.getMessage();
        if(e instanceof MethodArgumentNotValidException){
            int start=message.lastIndexOf("[");
            int end=message.lastIndexOf("]");
            message=message.substring(start+1,end-1);
            errorResponse.setError("Payload invalid");
        }else if(e instanceof ConstraintViolationException){
            message=message.substring(message.indexOf(" ")+1);
            errorResponse.setError("Parameter invalid");
        }

        errorResponse.setMessage(message);
        return errorResponse;
    }
}
