package com.example.exception;

public class CommandValidationFailedException extends RuntimeException
{
    public CommandValidationFailedException(String message)
    {
        super(message);
    }
}
