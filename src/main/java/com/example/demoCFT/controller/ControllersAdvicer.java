package com.example.demoCFT.controller;

import com.example.demoCFT.exception.NoSuchIdException;
import com.example.demoCFT.exception.NotEnoughArgumentsException;
import com.example.demoCFT.exception.WrongPCformException;
import com.example.demoCFT.exception.WrongScreenSizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllersAdvicer {
    //ловлю ошибки, чтобы подсказать причину их возникновения

    @ResponseBody
    @ExceptionHandler(NotEnoughArgumentsException.class)
    String notEnoughArgumentsHandler(NotEnoughArgumentsException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongPCformException.class)
    String WrongFormHandler(WrongPCformException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoSuchIdException.class)
    String idHandler(NoSuchIdException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    String digitHandler(IllegalArgumentException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongScreenSizeException.class)
    String screenSizeHandler(WrongScreenSizeException e){
        return e.getMessage();
    }

}
