package com.example.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import com.example.Main;
import com.example.exception.CommandExecutionFailedException;
import com.example.exception.CommandValidationFailedException;
import com.example.exception.InvalidFileInputFoundException;
import com.example.model.RepaymentDetails;

import org.hamcrest.collection.IsIterableContainingInRelativeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandExecutionServiceTest
{
    private CommandExecutionService commandExecutionService;

    @BeforeEach
    void setUp()
    {
        commandExecutionService = CommandExecutionServiceImpl.getInstance();
    }

    @Test
    void shouldReturnData() throws CommandExecutionFailedException
    {
        Collection<RepaymentDetails> repaymentDetails = commandExecutionService.doExecuteCommands(getFilePath("valid_input.txt"));
        assertEquals(4, repaymentDetails.size());

        assertThat(repaymentDetails, IsIterableContainingInRelativeOrder.<RepaymentDetails>containsInRelativeOrder(
            allOf(
                hasProperty("bankName", is("IDIDI")),
                hasProperty("borrowerName", is("Dale")),
                hasProperty("amountPayed", is(1000)),
                hasProperty("remainingEmi", is(55))
            ),
            allOf(
                hasProperty("bankName", is("IDIDI")),
                hasProperty("borrowerName", is("Dale")),
                hasProperty("amountPayed", is(8000)),
                hasProperty("remainingEmi", is(20))
            ),
            allOf(
                hasProperty("bankName", is("MBI")),
                hasProperty("borrowerName", is("Harry")),
                hasProperty("amountPayed", is(1044)),
                hasProperty("remainingEmi", is(12))
            ),
            allOf(
                hasProperty("bankName", is("MBI")),
                hasProperty("borrowerName", is("Harry")),
                hasProperty("amountPayed", is(0)),
                hasProperty("remainingEmi", is(24))
            )
        ));
    }

    @Test
    void shouldRecalculateRepaymentWhenPaymentMade() throws CommandExecutionFailedException
    {
        Collection<RepaymentDetails> repaymentDetails = commandExecutionService.doExecuteCommands(getFilePath("payment_command.txt"));
        assertEquals(1, repaymentDetails.size());
        assertThat(repaymentDetails, IsIterableContainingInRelativeOrder.<RepaymentDetails>containsInRelativeOrder(
            allOf(
                hasProperty("bankName", is("IDIDI")),
                hasProperty("borrowerName", is("Dale")),
                hasProperty("amountPayed", is(3652)),
                hasProperty("remainingEmi", is(4))
            )
        ));
    }

    @Test
    void shouldReportErrorWhenLoanDetailsMissing() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("no_loan_input.txt"));

        }
        catch (CommandValidationFailedException e)
        {
            assertEquals("Dale does not have a loan account at IDIDI", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenFieldEmpty() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("field_empty_input.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Mandatory field not found", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenLoanDetailsMissingValues() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("loan_details_missing.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Invalid loan command", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenPaymentDetailsMissingValues() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("payment_details_missing.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Invalid payment command", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenBalanceDetailsMissingValues() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("balance_details_missing.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Invalid balance command", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenInvalidTransactionType() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("invalid_transaction.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Error in transforming command.LOAN_Invalid IDIDI Dale 10000 5 4", e.getMessage());
        }
    }

    @Test
    void shouldReportErrorWhenInvalidPrinciple() throws CommandExecutionFailedException
    {
        try
        {
            commandExecutionService.doExecuteCommands(getFilePath("invalid_principle.txt"));

        }
        catch (InvalidFileInputFoundException e)
        {
            assertEquals("Error in transforming command.LOAN IDIDI Dale 10000XXX 5 4", e.getMessage());
        }
    }

    private static Path getFilePath(String fileName)
    {
        String filePath = Main.class.getClassLoader().getResource(fileName).getPath();
        return Paths.get(filePath);
    }
}