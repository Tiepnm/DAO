package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.model.TransactionDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount
{
    private BankingAccountDAO bankingAccountDAO;
    private TransactionDTO transactionDTO = new TransactionDTO();
    private Transaction transaction;

    public void setBankingAccountDAO(BankingAccountDAO bankingAccountDAO)
    {
        this.bankingAccountDAO = bankingAccountDAO;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public TransactionDTO getTransactionDTO()
    {
        return transactionDTO;
    }

    private void doTransaction(String accountNumber, Double amount, String description)
    {
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription(description);
        transaction.save(transactionDTO);
    }

    public com.qsoft.model.BankAccount openAccount(String accountNumber)
    {
        com.qsoft.model.BankAccount bankingAccountDTO = new com.qsoft.model.BankAccount();
        bankingAccountDTO.setAccountNumber(accountNumber);

        bankingAccountDAO.save(bankingAccountDTO);

        doTransaction(accountNumber, 0.0, "");
        return bankingAccountDTO;
    }

    public com.qsoft.model.BankAccount getAccount(String accountNumber)
    {

        return bankingAccountDAO.findOne(accountNumber);


    }

    public void deposit(com.qsoft.model.BankAccount bankingAccountDTO, double amount)
    {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() + amount);

        bankingAccountDAO.save(bankingAccountDTO);

        doTransaction(bankingAccountDTO.getAccountNumber(), amount, "Deposit");

    }

    public void withDraw(com.qsoft.model.BankAccount bankAccountDTO, double amount)
    {
        if (bankAccountDTO.getBalance() < amount)
        {
            throw new RuntimeException("Exception With Draw");
        }
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() - amount);

        bankingAccountDAO.save(bankAccountDTO);

        doTransaction(bankAccountDTO.getAccountNumber(), -amount, "With Draw");

    }

    public List<TransactionDTO> getAllTransactions(String accountNumber)
    {
        return transaction.getListTransaction(accountNumber);
    }

    public List<TransactionDTO> getAllTransactionsBetweenTime(String accountNumber, long timeStart, long timeEnd)
    {
        return transaction.getAllTransactionBetweenTime(accountNumber, timeStart, timeEnd);
    }

    public List<TransactionDTO> getNTransactions(String accountNumber,Integer number)
    {
        return transaction.getNTransactions(accountNumber,number);
    }
}
