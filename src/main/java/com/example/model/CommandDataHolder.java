package com.example.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandDataHolder
{
    private final Map<String, List<Command>> commandDataHolder;
    private final Set<String> balanceCommands;

    public CommandDataHolder()
    {
        commandDataHolder = new LinkedHashMap();
        balanceCommands = new LinkedHashSet<>();
    }

    public void addCommand(Command command)
    {
        String key = getHolderKey(command);

        commandDataHolder.putIfAbsent(key, new ArrayList());
        commandDataHolder.get(key).add(command);

        if (command.getActionType().equals(CommandType.BALANCE))
        {
            balanceCommands.add(key);
        }
    }

    public Map<String, List<Command>> getCommands()
    {
        return commandDataHolder;
    }

    public Set<String> getBalanceCommands()
    {
        return balanceCommands;
    }

    private String getHolderKey(Command command)
    {
        return command.getBankName().toLowerCase().concat("_").concat(command.getBorrowerName().toLowerCase());
    }
}
