package com.example.demoCFT.exception;

//недостаточно аргументов
public class NotEnoughArgumentsException extends RuntimeException{
    public NotEnoughArgumentsException(String s){
        super(s);
    }
}
