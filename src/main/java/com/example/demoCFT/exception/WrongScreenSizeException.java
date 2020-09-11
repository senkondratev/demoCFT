package com.example.demoCFT.exception;

//введенный пользователем размер экрана не соответствует ни одному из допустимых
public class WrongScreenSizeException extends RuntimeException{
    public WrongScreenSizeException(String s){
        super(s);
    }
}
