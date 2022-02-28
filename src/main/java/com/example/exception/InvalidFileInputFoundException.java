package com.example.exception;

public class InvalidFileInputFoundException extends RuntimeException
{
    public InvalidFileInputFoundException(String message)
    {
        super(message);
    }

    public InvalidFileInputFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
