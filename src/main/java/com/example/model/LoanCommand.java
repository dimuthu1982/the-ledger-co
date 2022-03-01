package com.example.model;

import java.math.BigDecimal;

public class LoanCommand extends Command
{
    private BigDecimal principal;
    private int numberOfYears;
    private int interestRate;

    public BigDecimal getPrincipal()
    {
        return principal;
    }

    public void setPrincipal(BigDecimal principal)
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
}
