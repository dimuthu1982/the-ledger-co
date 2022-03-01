package com.example.model;

import java.math.BigDecimal;

public class RepaymentDetails
{
    private String bankName;
    private String borrowerName;
    private BigDecimal amountPayed;
    private int remainingEmi;

    public RepaymentDetails(String bankName, String borrowerName, BigDecimal amountPayed, int remainingEmi)
    {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.amountPayed = amountPayed;
        this.remainingEmi = remainingEmi;
    }

    public BigDecimal getAmountPayed()
    {
        return amountPayed;
    }

    public int getRemainingEmi()
    {
        return remainingEmi;
    }

    public String getBankName()
    {
        return bankName;
    }

    public String getBorrowerName()
    {
        return borrowerName;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s %s", bankName, borrowerName, amountPayed, remainingEmi);
    }
}
