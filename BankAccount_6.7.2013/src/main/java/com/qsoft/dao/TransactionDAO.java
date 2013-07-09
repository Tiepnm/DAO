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
 * Date: 1/7/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO extends JpaRepository<TransactionDTO, Long>,TransactionDAOCustom
{


}
