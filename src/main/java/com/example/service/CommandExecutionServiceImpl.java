package com.example.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.common.FileDataTransformer;
import com.example.exception.CommandExecutionFailedException;
import com.example.model.Command;
import com.example.model.CommandDataHolder;
import com.example.model.RepaymentDetails;
import com.example.service.file.FileContentTransformerService;
import com.example.service.file.TextFileCommandTransformer;
import com.example.service.transaction.RepaymentCalculationService;
import com.example.service.transaction.RepaymentCalculationServiceImpl;
import com.example.service.validate.CommandDataValidator;

public class CommandExecutionServiceImpl implements CommandExecutionService
{
    private RepaymentCalculationService calculationService;
    private FileDataTransformer fileDataTransformer;

    private static final CommandExecutionService instance = new CommandExecutionServiceImpl();

    public static CommandExecutionService getInstance()
    {
        return instance;
    }

    private CommandExecutionServiceImpl()
    {
        calculationService = RepaymentCalculationServiceImpl.getInstance();
        fileDataTransformer = TextFileCommandTransformer.getInstance() ;
    }

    public List<RepaymentDetails> doExecuteCommands(Path filePath) throws CommandExecutionFailedException
    {
        CommandDataHolder commandDataHolder = new FileContentTransformerService(filePath).getCommandHolder(fileDataTransformer);

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
