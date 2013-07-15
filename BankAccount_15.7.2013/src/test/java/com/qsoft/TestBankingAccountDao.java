package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.model.BankAccount;
import com.qsoft.model.TransactionDTO;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 7/3/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestBankingAccountDao
{
    @Autowired
    private BankingAccountDAO bankingAccountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private DataSource dataSource;

    private IDatabaseTester databaseTester;


    private String accountNumber = "0123456789";

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception
    {
        databaseTester = new DataSourceDatabaseTester(dataSource);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private TransactionDTO createTransaction(Long webId, String accountNumber, Double amount, Long timeStamp,String description)
    {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setAmount(amount);
        transactionDTO.setWebId(webId);
        transactionDTO.setTimeStamp(timeStamp);
        transactionDTO.setDescription(description);
        transactionDAO.save(transactionDTO);
        return transactionDTO;
    }

    private void initTransactionForAccount(String account)
    {
        createTransaction(2L, account, 1000.0, 1000L,"create");
        createTransaction(3L, account, 1000.0, 1500L,"create");
        createTransaction(4L, account, 1000.0, 2000L,"create");
        createTransaction(5L, account, 1000.0, 3000L,"create");
    }

    @After
    public void tearDown() throws Exception
    {
        databaseTester.onTearDown();
    }

    @Test
    public void testCanOpenAccount() throws Exception
    {

        BankAccount model = new BankAccount("1ab22323232", 323232.0);
        BankAccount account = bankingAccountDAO.save(model);

        assertEquals("1ab22323232", account.getAccountNumber());
        assertEquals(323232.0, account.getBalance());
    }

    @Test
    public void testCanUpdateExistAccount()
    {
        BankAccount model = new BankAccount("1ab22323232", 323232.0);
        model.setBalance(123456);
        BankAccount account = bankingAccountDAO.save(model);

        assertEquals("1ab22323232", account.getAccountNumber());
        assertEquals(123456.0, account.getBalance());
    }

    @Test
    public void testCanFindExistAccount() throws Exception
    {
        BankAccount account = bankingAccountDAO.findOne(accountNumber);

        assertEquals(accountNumber, account.getAccountNumber());
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testCannotFindAccountNotExist()
    {
        String accountNumber = "1234567832329";
        BankAccount getBankAccount = bankingAccountDAO.findOne(accountNumber);
        assertEquals(null, getBankAccount);
    }

    @Test
    public void testCanCreateTransactionOfAccount()
    {
        createTransaction(2L, accountNumber, 2000.0, 1000L,"");
        List<TransactionDTO> list = transactionDAO.getAllTransaction(accountNumber);

        assertEquals(2, list.size());
        assertEquals(2000.0, list.get(1).getAmount());
    }

    @Test
    public void testCanGetTransactionOfAccount()
    {
        List<TransactionDTO> list = transactionDAO.getAllTransaction(accountNumber);

        assertEquals(1, list.size());
        assertEquals(10000.0, list.get(0).getAmount());
    }

    @Test
    public void testCanGetAllTransactionBetweenTimeOfAccount()
    {
        initTransactionForAccount(accountNumber);
        List<TransactionDTO> list = transactionDAO.getAllTransactionsBetweenTime(accountNumber, 1000L, 2000L);

        assertEquals(3, list.size());
    }

    @Test
    public void testGetNTransactionsOfAccount()
    {
        initTransactionForAccount(accountNumber);

        List<TransactionDTO> list = transactionDAO.getNTransactions(accountNumber, 2);
        assertEquals(2, list.size());
        assertEquals(5L, list.get(0).getWebId(), 0);
        assertEquals(4L, list.get(1).getWebId(), 0);
    }


}
