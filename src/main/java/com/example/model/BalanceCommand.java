package com.example.model;

public class BalanceCommand extends Command implements Repayment
{
    private int emiNo;

    public int getEmiNo()
    {
        return emiNo;
    }

    public void setEmiNo(int emiNo)
    {
        this.emiNo = emiNo;
    }
}
