package com.kro.business.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * locustom - com.kro.business.exception
 * <p>
 * Created by Thomas Croguennec on 04/01/17.
 * On 04/01/17
 */
@Slf4j
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Message illisible ! Exception : " + ex.getLocalizedMessage();
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "La méthode de prend en charge ces paramètres. Exception : " + ex.getLocalizedMessage() ;
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    // 401
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ApiError badCredentialsExceptionHandler(BadCredentialsException e) {
//        log.error("Erreur : {} ", e.getMessage());
//        return new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getLocalizedMessage());
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ApiError authenticationExceptionHandler(AuthenticationException e) {
//        log.error("Erreur : {} ", e.getMessage());
//        return new ApiError(HttpStatus.UNAUTHORIZED.value(), e.getLocalizedMessage());
//    }
//
//    // 403
//    @ExceptionHandler({ AccessDeniedException.class })
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    public ApiError handleAccessDenied(final AccessDeniedException ex, final WebRequest request) {
//        log.error("Erreur : {} ", ex.getMessage());
//        return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
//    }
    // 404

    @ExceptionHandler(value = { EntityNotFoundException.class, ClassNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409
//    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ResponseBody
//    protected ApiError handleConflict(final RuntimeException ex, final WebRequest request) {
//        log.error("Erreur : {} ", ex.getMessage());
//        return new ApiError(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
//    }
//
//    // 500
//    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class, TransactionException.class})
//    public ApiError handleInternal(final RuntimeException exception, final WebRequest request) {
//        log.error("Erreur : {} ", exception.getLocalizedMessage());
//        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage());
//    }
//
//    // Other Exception
//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ApiError handleOtherException(Exception exception, WebRequest request){
//        log.error("Erreur : {}", exception.getLocalizedMessage());
//        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage());
//
//    }

}
