package com.qsoft.dao;

import com.qsoft.model.TransactionDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/8/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAOCustom
{
    @Query("select tr from TransactionDTO tr where tr.accountNumber=:accountNumber")
    public List<TransactionDTO> getAllTransaction(@Param("accountNumber")String accountNumber);

//    public List<TransactionDTO> getAllTransactionsBetweenTime(String accountNumber, long timeStart, long timeEnd);
//
//    public List<TransactionDTO> getNTransactions(String accountNumber);
}

