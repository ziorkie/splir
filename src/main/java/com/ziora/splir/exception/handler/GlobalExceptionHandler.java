package com.ziora.splir.exception.handler;

import com.ziora.splir.exception.InvitationNotFoundException;
import com.ziora.splir.exception.UserNotFoundException;
import com.ziora.splir.payload.ErrorPayload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Log log = LogFactory.getLog(getClass());


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorPayload> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorPayload errorResponse = new ErrorPayload("Unknown exception", details);
        log.warn(ex);
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorPayload errorPayload = new ErrorPayload("Validation failed.", details);
        return new ResponseEntity(errorPayload, HttpStatus.BAD_REQUEST);
    }
//    TEMPLATE FOR EXCEPTION HANDLING
//    @ExceptionHandler(CHANGE_ME.class)
//    public ResponseEntity<ErrorPayload> handleCHANGE_ME(Exception ex){
//        List<String> details = new ArrayList<>();
//        details.add(ex.getLocalizedMessage());
//        ErrorPayload errorPayload = new ErrorPayload("CHANGE_MY_MESSAGE", details);
//        log.warn(ex);
//        return new ResponseEntity(errorPayload, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

        @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorPayload> handleUserNotFoundException(Exception ex){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorPayload errorPayload = new ErrorPayload("User not found!", details);
        log.warn(ex);
        return new ResponseEntity(errorPayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvitationNotFoundException.class)
    public ResponseEntity<ErrorPayload> handleInvitationNotFoundException(Exception ex){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorPayload errorPayload = new ErrorPayload("Invitation not found!", details);
        log.warn(ex);
        return new ResponseEntity(errorPayload, HttpStatus.NOT_FOUND);
    }
}