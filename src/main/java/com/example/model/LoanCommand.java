package com.example.model;

public class LoanCommand extends Command
{
    private int principal;
    private int numberOfYears;
    private int interestRate;

    public int getPrincipal()
    {
        return principal;
    }

    public void setPrincipal(int principal)
    {
        this.principal = principal;
    }

    public int getNumberOfYears()
    {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears)
    {
        this.numberOfYears = numberOfYears;
    }

    public int getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(int interestRate)
    {
        this.interestRate = interestRate;
    }


    @Override
    public String toString()
    {
        return "LoanCommand{" +
            "actionType=" + getActionType() +
            ", bankName=" + getBankName() +
            ", borrowerName=" + getBorrowerName() +
            ", principal=" + principal +
            ", numberOfYears=" + numberOfYears +
            ", interestRate=" + interestRate +
            '}';
    }
}
