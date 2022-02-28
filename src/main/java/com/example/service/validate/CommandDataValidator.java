package com.example.service.validate;

import java.util.List;
import java.util.Map;

import com.example.exception.CommandValidationFailedException;
import com.example.model.Command;
import com.example.model.CommandDataHolder;
import com.example.model.CommandType;

public class CommandDataValidator
{
    private static final CommandDataValidator instance = new CommandDataValidator();

    private CommandDataValidator()
    {
    }

    public static CommandDataValidator getInstance(){
        return instance;
    }

    public void validate(CommandDataHolder commandDataHolder)
    {
        Map<String, List<Command>> commands = commandDataHolder.getCommands();
        List<Command> commandSequence;
        for(String key : commands.keySet()) {
            commandSequence = commands.get(key);
            validateSequence(commandSequence);
        }
    }

    private void validateSequence(List<Command> commandSequence)
    {
         Command loanCommand = commandSequence.get(0);
        if(!loanCommand.getActionType().equals(CommandType.LOAN))
        {
            throw new CommandValidationFailedException(String.format("%s does not have a loan account at %s", loanCommand.getBorrowerName(), loanCommand.getBankName())) ;
        }
    }
}
