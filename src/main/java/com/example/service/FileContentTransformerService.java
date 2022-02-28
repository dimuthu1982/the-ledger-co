package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.example.common.FileDataTransformer;
import com.example.exception.CommandExecutionFailedException;
import com.example.model.CommandDataHolder;

public class FileContentTransformerService
{
    private final Path filePath;

    public FileContentTransformerService(Path filePath)
    {
        this.filePath = filePath;
    }

    public CommandDataHolder getCommandHolder(FileDataTransformer transformer) throws CommandExecutionFailedException
    {
        CommandDataHolder commandDataHolder = new CommandDataHolder();

        try (Stream<String> stream = Files.lines(filePath))
        {
            stream
                .map(transformer::transformToCommand)
                .forEach(commandDataHolder::addCommand);

        }
        catch (IOException e)
        {
            throw new CommandExecutionFailedException(e);
        }
        return commandDataHolder;
    }

}
