package com.example.util;

public class CalculationUtil
{
    public static int calculateEmi(int principleAmount, int numberOfYears)
    {
        double amountPayedWithInterest = principleAmount;

        double emi = Math.ceil((amountPayedWithInterest / (numberOfYears * 12)));
        return (int) emi;
    }

    /**
     * Calculates the total loan including the interest.
     *
     * @param principleAmount loan amount.
     * @param numberOfYears   years the loan for.
     * @param interestRate    interest rate.
     * @return total loan amount.
     */
    public static int calculateTotalLoan(int principleAmount, int numberOfYears, int interestRate)
    {
        double loanInterest = Math.ceil((principleAmount * numberOfYears * interestRate) / 100);

        double amountPayedWithInterest = Double.valueOf(principleAmount) + loanInterest;
        return (int) amountPayedWithInterest;
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
    public static int calculateRemainingEmis(int remainingLoan, int eni)
    {
        return (int) Math.ceil((double) remainingLoan / eni);
    }
}
