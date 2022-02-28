package com.example.exception;

public class CommandExecutionFailedException extends Exception
{
    public CommandExecutionFailedException(Throwable cause)
    {
        super(cause);
    }
}
