package com.example.demoCFT.exception;

//введенный пользователем форм-фактор не соответствует ни одному из допустимых
public class WrongPCformException extends RuntimeException{
    public WrongPCformException(String s){
        super(s);
    }
}
