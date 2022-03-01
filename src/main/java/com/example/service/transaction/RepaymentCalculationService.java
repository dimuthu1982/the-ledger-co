package com.example.service.transaction;

import java.util.List;

import com.example.model.Command;
import com.example.model.RepaymentDetails;

public interface RepaymentCalculationService
{
    /**
     * Calculates the repayments based on the transactions
     * @param commands list of commands executed against the bank
     * @return repayment data.
     */
    List<RepaymentDetails> doCalculateRepayment(List<Command> commands);
}
