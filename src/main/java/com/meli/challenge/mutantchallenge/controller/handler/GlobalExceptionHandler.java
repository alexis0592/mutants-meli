package com.meli.challenge.mutantchallenge.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler({
//            MethodArgumentNotValidException.class,
//            HttpMessageNotReadableException.class
//    })
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponseDTO methodArgumentNotValidException(HttpServletRequest request) {
//        return ObjectHelper.getBadRequestError("", request.getRequestURI(), Constants.RESPONSE_CODE_0400, Constants.RESPONSE_MESSAGE_0400);
//    }


}
