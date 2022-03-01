package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.exception.CommandExecutionFailedException;
import com.example.model.RepaymentDetails;
import com.example.service.CommandExecutionServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main
{
    private static final Log LOGGER = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws CommandExecutionFailedException
    {
        List<RepaymentDetails> loanDetails = CommandExecutionServiceImpl.getInstance().doExecuteCommands(getFilePath("input1.txt"));

        LOGGER.info("OUTPUT:");
        loanDetails.forEach(LOGGER::info);
    }

    private static Path getFilePath(String fileName)
    {
        String filePath = Main.class.getClassLoader().getResource(fileName).getPath();
        return Paths.get(filePath);
    }
}
