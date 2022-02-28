package com.example.service;

import java.nio.file.Path;
import java.util.List;

import com.example.exception.CommandExecutionFailedException;
import com.example.model.RepaymentDetails;

/**
 * Execute the commands found in the file
 */
public interface CommandExecutionService
{
    /**
     * Returns a list of repayments defined in the file
     * @param filePath contains the loan details
     * @return list of repayments
     * @throws CommandExecutionFailedException when an error on transforming file to a commands.
     */
    List<RepaymentDetails> doExecuteCommands(Path filePath) throws CommandExecutionFailedException;
}
