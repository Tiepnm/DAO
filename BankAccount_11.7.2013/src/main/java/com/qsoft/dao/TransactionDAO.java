package com.qsoft.dao;

import com.qsoft.model.BankAccount;
import com.qsoft.model.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/9/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO extends JpaRepository<TransactionDTO, Long>,TransactionDAOCustom
{

    @Query("select tr from TransactionDTO tr where tr.accountNumber=:accountNumber")
    public List<TransactionDTO> getAllTransaction(@Param("accountNumber")String accountNumber);

    @Query("select tr from TransactionDTO tr where tr.accountNumber=:accountNumber and tr.timeStamp >= :timeStart and tr.timeStamp <= :timeEnd")
    public List<TransactionDTO> getAllTransactionsBetweenTime(@Param("accountNumber")String accountNumber,@Param("timeStart") long timeStart, @Param("timeEnd") long timeEnd);

}
