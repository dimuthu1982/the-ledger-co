package com.example.service.impl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.exception.CommandExecutionFailedException;
import com.example.model.Command;
import com.example.model.CommandDataHolder;
import com.example.model.RepaymentDetails;
import com.example.service.CommandExecutionService;
import com.example.service.FileContentTransformerService;
import com.example.service.RepaymentCalculationService;
import com.example.service.TextFileCommandTransformer;
import com.example.service.transaction.RepaymentCalculationServiceImpl;
import com.example.service.validate.CommandDataValidator;

public class CommandExecutionServiceImpl implements CommandExecutionService
{
    private RepaymentCalculationService calculationService;

    public CommandExecutionServiceImpl()
    {
        calculationService = RepaymentCalculationServiceImpl.getInstance();
    }

    public List<RepaymentDetails> doExecuteCommands(Path filePath) throws CommandExecutionFailedException
    {
        CommandDataHolder commandDataHolder = new FileContentTransformerService(filePath).getCommandHolder(new TextFileCommandTransformer());

        CommandDataValidator.getInstance().validate(commandDataHolder);

        List<RepaymentDetails> loanDetailsList = new ArrayList();
        List<RepaymentDetails> loanDetails;
        Map<String, List<Command>> commands = commandDataHolder.getCommands();
        for (String key : commandDataHolder.getBalanceCommands())
        {
            loanDetails = calculationService.doCalculateRepayment(commands.get(key));
            loanDetailsList.addAll(loanDetails);
        }
        return loanDetailsList;
    }
}
