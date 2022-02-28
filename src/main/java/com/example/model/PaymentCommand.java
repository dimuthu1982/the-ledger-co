package com.example.model;

public class PaymentCommand extends Command implements Repayment
{
    private int lumpSumAmount;
    private int emiNo;

    public int getLumpSumAmount()
    {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(int lumpSumAmount)
    {
        this.lumpSumAmount = lumpSumAmount;
    }

    public int getEmiNo()
    {
        return emiNo;
    }

    public void setEmiNo(int emiNo)
    {
        this.emiNo = emiNo;
    }

    @Override
    public String toString()
    {
        return "PaymentCommand{" +
            "actionType=" + getActionType() +
            ", bankName=" + getBankName() +
            ", borrowerName=" + getBorrowerName() +
            ", lumpSumAmount=" + lumpSumAmount +
            ", emiNo=" + emiNo +
            '}';
    }
}
