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

    @After
    public void tearDown() throws Exception
    {
        databaseTester.onTearDown();
    }


    @Test
    public void testOpenAccount() throws Exception
    {

        BankAccount model = new BankAccount("1234565454", 323232.0);
        BankAccount account = bankingAccountDAO.save(model);
        assertEquals("1234565454", account.getAccountNumber());
    }

    @Test
    public void testGetAccount() throws Exception
    {

        BankAccount account = bankingAccountDAO.findOne("0123456789");
        assertEquals("0123456789", account.getAccountNumber());
    }

    @Test
    public void testCreateTransaction()
    {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountNumber("0123456789");
        transactionDTO.setAmount(1000.0);
        transactionDTO.setWebId(12L);
        transactionDTO.setDescription("create");
        transactionDAO.save(transactionDTO);
        List<TransactionDTO> list = transactionDAO.getAllTransaction("0123456789");

        assertEquals(2, list.size());
        assertEquals(1000.0, list.get(1).getAmount());
    }


}
