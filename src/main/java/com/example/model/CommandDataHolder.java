package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandDataHolder
{
    private final Map<String, List<Command>> commandDataMap;
    private final Set<String> balanceCommands;

    public CommandDataHolder()
    {
        commandDataMap = new HashMap();
        balanceCommands = new HashSet();
    }

    public void addCommand(Command command)
    {
        String key = getHolderKey(command);

        commandDataMap.putIfAbsent(key, new ArrayList());
        commandDataMap.get(key).add(command);

        if (command.getActionType().equals(CommandType.BALANCE))
        {
            balanceCommands.add(key);
        }
    }

    public Map<String, List<Command>> getCommands()
    {
        return commandDataMap;
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
