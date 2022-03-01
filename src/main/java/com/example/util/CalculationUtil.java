package com.example.util;

import java.math.BigDecimal;

public class CalculationUtil
{
    private CalculationUtil()
    {
    }

    public static int calculateEmi(BigDecimal loanWithInterest, int numberOfYears)
    {
        return (int) Math.ceil((loanWithInterest.doubleValue() / (numberOfYears * 12)));
    }

    /**
     * Calculates the total loan including the interest.
     *
     * @param principleAmount loan amount.
     * @param numberOfYears   years the loan for.
     * @param interestRate    interest rate.
     * @return total loan amount.
     */
    public static BigDecimal calculateTotalLoan(BigDecimal principleAmount, int numberOfYears, int interestRate)
    {

        double totalLoan = (principleAmount.doubleValue() * numberOfYears * interestRate) / 100;
        double loanInterest = Math.ceil(totalLoan);

        return BigDecimal.valueOf(principleAmount.doubleValue() + loanInterest);
    }

    /**
     * Calculates the amount payed so far.
     *
     * @param emiAmount    emi payment
     * @param noOfEmiPayed number of emi payed
     * @return total amount payed
     */
    public static int calculateTotalAmountPayed(int emiAmount, int noOfEmiPayed)
    {
        return emiAmount * noOfEmiPayed;
    }

    /**
     * @param remainingLoan
     * @param eni
     * @return
     */
    public static int calculateRemainingEmis(BigDecimal remainingLoan, int eni)
    {
        return (int) Math.ceil(remainingLoan.doubleValue() / eni);
    }
}
