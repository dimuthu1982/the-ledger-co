package com.example.model;

public abstract class Command
{
    private CommandType actionType;
    private String bankName;
    private String borrowerName;

    public CommandType getActionType()
    {
        return actionType;
    }

    public void setActionType(CommandType actionType)
    {
        this.actionType = actionType;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBorrowerName()
    {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName)
    {
        this.borrowerName = borrowerName;
    }

}
