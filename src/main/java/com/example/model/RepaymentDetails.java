package com.example.model;

public class RepaymentDetails
{
    private String bankName;
    private String borrowerName;
    private int amountPayed;
    private int remainingEmi;

    public RepaymentDetails(String bankName, String borrowerName, int amountPayed, int remainingEmi)
    {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.amountPayed = amountPayed;
        this.remainingEmi = remainingEmi;
    }

    public int getAmountPayed()
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
}
