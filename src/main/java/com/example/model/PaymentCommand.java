package com.example.model;

import java.math.BigDecimal;

public class PaymentCommand extends Command implements Repayment
{
    private BigDecimal lumpSumAmount;
    private int emiNo;

    public BigDecimal getLumpSumAmount()
    {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(BigDecimal lumpSumAmount)
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
