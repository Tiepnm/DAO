package com.qsoft.dao;

import com.qsoft.model.TransactionDTO;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/9/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionImpl implements TransactionDAOCustom
{

    @PersistenceContext
    EntityManager em;

    public List<TransactionDTO> getNTransactions(String accountNumber, Integer number)
    {
        Query query = em.createQuery("select tr from TransactionDTO tr");
        return query.getResultList();
    }
}
