package com.example.service;

import com.example.common.FileDataTransformer;
import com.example.exception.InvalidFileInputFoundException;
import com.example.model.BalanceCommand;
import com.example.model.Command;
import com.example.model.CommandType;
import com.example.model.LoanCommand;
import com.example.model.PaymentCommand;

public class TextFileCommandTransformer implements FileDataTransformer
{
    public static final String SPACE = " ";

    @Override
    public Command transformToCommand(String rawData)
    {
        try
        {
            return createCommand(rawData);
        }
        catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
        {
            throw new InvalidFileInputFoundException("Error in transforming command." + rawData, e);
        }
    }

    private Command createCommand(String rawData)
    {
        String[] data = rawData.split(SPACE);
        String command = data[0];

        Command commandDetail = null;

        CommandType commandType = CommandType.valueOf(command);
        switch (commandType)
        {
            case LOAN:
                commandDetail = getLoanCommand(data);
                break;
            case PAYMENT:
                commandDetail = getPaymentCommand(data);
                break;
            case BALANCE:
                commandDetail = getBalanceCommand(data);
                break;
        }
        return commandDetail;
    }

    private Command getLoanCommand(String[] data)
    {
        if (data.length != 6)
        {
            throw new InvalidFileInputFoundException("Invalid loan command");
        }
        LoanCommand command = new LoanCommand();
        command.setActionType(CommandType.valueOf(data[0]));
        command.setBankName(getRequiredValue(data[1]));
        command.setBorrowerName(getRequiredValue(data[2]));
        command.setPrincipal(Integer.parseInt(getRequiredValue(data[3])));
        command.setNumberOfYears(Integer.parseInt(getRequiredValue(data[4])));
        command.setInterestRate(Integer.parseInt(getRequiredValue(data[5])));
        return command;
    }

    private Command getPaymentCommand(String[] data)
    {
        if (data.length != 5)
        {
            throw new InvalidFileInputFoundException("Invalid payment command");
        }
        PaymentCommand command = new PaymentCommand();
        command.setActionType(CommandType.valueOf(data[0]));
        command.setBankName(getRequiredValue(getRequiredValue(data[1])));
        command.setBorrowerName(getRequiredValue(data[2]));
        command.setLumpSumAmount(Integer.parseInt(getRequiredValue(data[3])));
        command.setEmiNo(Integer.parseInt(getRequiredValue(data[4])));
        return command;
    }

    private Command getBalanceCommand(String[] data)
    {
        if (data.length != 4)
        {
            throw new InvalidFileInputFoundException("Invalid balance command");
        }
        BalanceCommand command = new BalanceCommand();
        command.setActionType(CommandType.valueOf(data[0]));
        command.setBankName(getRequiredValue(data[1]));
        command.setBorrowerName(getRequiredValue(data[2]));
        command.setEmiNo(Integer.parseInt(getRequiredValue(data[3])));
        return command;
    }

    private String getRequiredValue(String value)
    {
        if (value != null && !value.trim().isEmpty())
        {
            return value.trim();
        }
        throw new InvalidFileInputFoundException("Mandatory field not found");
    }
}
