package com.qsoft.dao;

import com.qsoft.model.TransactionDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/9/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAOCustom
{
    public List<TransactionDTO> getNTransactions(String accountNumber,Integer number);
}

