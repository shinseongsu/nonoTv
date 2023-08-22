package com.nonotv.codec.global.exception;

public class EmptyFileException extends RuntimeException{

    private static final String MESSAGE = "파일이 존재 하지 않습니다.";

    public EmptyFileException() {
        super(MESSAGE);
    }
}
