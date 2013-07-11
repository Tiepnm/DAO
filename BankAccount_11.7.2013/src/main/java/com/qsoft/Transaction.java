package com.qsoft;

import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    private TransactionDAO transactionDAO;

    public void setTransactionDao(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public void save(TransactionDTO transactionDTO) {
        transactionDAO.save(transactionDTO);
    }
    public List<TransactionDTO> getListTransaction(String accountNumber) {
        return transactionDAO.getAllTransaction(accountNumber);
    }
    public List<TransactionDTO> getAllTransactionBetweenTime(String accountNumber,long timeStart,long timeEnd) {
        return transactionDAO.getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
    }

    public List<TransactionDTO> getNTransactions(String accountNumber,Integer number) {
       return transactionDAO.getNTransactions(accountNumber,number);
    }
}
