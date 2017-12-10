package com.github.rudge.poc.web.handler;

import com.github.rudge.poc.domain.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.Set;

import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @RequestMapping(produces = { APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class, MethodArgumentTypeMismatchException.class })
    public @ResponseBody
    ResponseError handleValidationException(Exception ex) throws IOException {
        LOG.error("Error bad request", ex);
        return new ResponseError(valueOf(BAD_REQUEST.value()), ex.getLocalizedMessage());
    }

    @RequestMapping(produces = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(value = UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public @ResponseBody ResponseError handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex)
            throws IOException {
        LOG.error("Error unsupported Media Type | {} ", ex.getSupportedMediaTypes(), ex);
        return new ResponseError(valueOf(UNSUPPORTED_MEDIA_TYPE.value()), ex.getLocalizedMessage());
    }

    @RequestMapping(produces = { APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseError handleUncaughtException(Exception ex) throws IOException {
        LOG.error("Occurred an unexpected error", ex);
        return new ResponseError(valueOf(INTERNAL_SERVER_ERROR.value()), ex.getLocalizedMessage());
    }
}
