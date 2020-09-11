package com.example.demoCFT.exception;

//если в базе не найдется элемента с указанным id
public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException(String s){
        super(s);
    }
}
