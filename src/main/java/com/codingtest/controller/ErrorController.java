package com.codingtest.controller;

import com.codingtest.dto.Meta;
import com.codingtest.dto.Response;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by arahansa on 2015-06-29.
 */
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleError405(Exception e)   {
        return Response.makeResponseWith(Meta.ERROR_NOTSUPPORT, null, HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    }
    @ExceptionHandler({HttpMessageNotWritableException.class, ConversionNotSupportedException.class})
    public ResponseEntity<?> handleError500(Exception e)   {
        return Response.makeResponseWith(Meta.ERROR_NOTSUPPORT, null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }




}
