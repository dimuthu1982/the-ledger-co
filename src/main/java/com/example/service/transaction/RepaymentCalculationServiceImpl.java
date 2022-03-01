package com.example.service.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.model.BalanceCommand;
import com.example.model.Command;
import com.example.model.CommandType;
import com.example.model.LoanCommand;
import com.example.model.PaymentCommand;
import com.example.model.RepaymentDetails;
import com.example.util.CalculationUtil;

public class RepaymentCalculationServiceImpl implements RepaymentCalculationService
{
    private static final RepaymentCalculationServiceImpl instance = new RepaymentCalculationServiceImpl();

    private RepaymentCalculationServiceImpl()
    {
    }

    public static RepaymentCalculationServiceImpl getInstance()
    {
        return instance;
    }

    @Override
    public List<RepaymentDetails> doCalculateRepayment(List<Command> commands)
    {
        LoanCommand loanCommand = (LoanCommand) commands.get(0);

        List<RepaymentDetails> loanDetailList = new ArrayList();

        RepaymentDetails loanDetails;

        BigDecimal principleAmount = loanCommand.getPrincipal();

        BigDecimal totalLoan = CalculationUtil.calculateTotalLoan(principleAmount, loanCommand.getNumberOfYears(), loanCommand.getInterestRate());
        int emi = CalculationUtil.calculateEmi(totalLoan, loanCommand.getNumberOfYears());

        int lumpSumPayment = 0;
        List<Command> transactions = commands.subList(1, commands.size());

        for (Command transaction : transactions)
        {
            if (CommandType.BALANCE.equals(transaction.getActionType()))
            {
                loanDetails = handleBalance(loanCommand, totalLoan, lumpSumPayment, emi, ((BalanceCommand) transaction).getEmiNo());
                loanDetailList.add(loanDetails);
            }
            else if (CommandType.PAYMENT.equals(transaction.getActionType()))
            {
                PaymentCommand paymentCommand = ((PaymentCommand) transaction);
                lumpSumPayment = paymentCommand.getLumpSumAmount().intValue();
            }
        }

        return loanDetailList;
    }

    private RepaymentDetails handleBalance(LoanCommand loanCommand, BigDecimal totalLoan, int lumpSumPayment, int emiAmount, int noOfEmiPayed)
    {
        BigDecimal totalAmountPayed = new BigDecimal(CalculationUtil.calculateTotalAmountPayed(emiAmount, noOfEmiPayed) + lumpSumPayment);

        int remainingEmis = CalculationUtil.calculateRemainingEmis(totalLoan.subtract(totalAmountPayed), emiAmount);

        return new RepaymentDetails(loanCommand.getBankName(), loanCommand.getBorrowerName(), totalAmountPayed, remainingEmis);
    }
}
