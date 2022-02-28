package com.example.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.Main;
import com.example.exception.CommandExecutionFailedException;
import com.example.exception.CommandValidationFailedException;
import com.example.exception.InvalidFileInputFoundException;
import com.example.model.RepaymentDetails;
import com.example.service.impl.CommandExecutionServiceImpl;

import org.hamcrest.collection.IsIterableContainingInRelativeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecutionServiceTest
{
    private CommandExecutionService commandExecutionService;

    @BeforeEach
    void setUp()
    {
        commandExecutionService = new CommandExecutionServiceImpl();
    }

    @Test
    void shouldReturnData() throws CommandExecutionFailedException
    {
        Collection<RepaymentDetails> repaymentDetails = commandExecutionService.doExecuteCommands(getFilePath("valid_input.txt"));
        assertEquals(repaymentDetails.size(), 4);

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
    public void shouldRecalculateRepaymentWhenPaymentMade() throws CommandExecutionFailedException
    {
        Collection<RepaymentDetails> repaymentDetails = commandExecutionService.doExecuteCommands(getFilePath("payment_command.txt"));
        assertEquals(repaymentDetails.size(), 1);
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
    public void shouldReportErrorWhenLoanDetailsMissing() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenFieldEmpty() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenLoanDetailsMissingValues() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenPaymentDetailsMissingValues() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenBalanceDetailsMissingValues() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenInvalidTransactionType() throws CommandExecutionFailedException
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
    public void shouldReportErrorWhenInvalidPrinciple() throws CommandExecutionFailedException
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

    private Collection<RepaymentDetails> getValidRepayments()
    {
        List<RepaymentDetails> repaymentDetails = new ArrayList(4);
        repaymentDetails.add(new RepaymentDetails("IDIDI", "Dale", 1000, 55));
        repaymentDetails.add(new RepaymentDetails("IDIDI", "Dale", 8000, 20));
        repaymentDetails.add(new RepaymentDetails("MBI", "Harry", 1044, 12));
        repaymentDetails.add(new RepaymentDetails("MBI", "Harry", 0, 24));
        return repaymentDetails;
    }

    private static Path getFilePath(String fileName)
    {
        String filePath = Main.class.getClassLoader().getResource(fileName).getPath();
        return Paths.get(filePath);
    }
}