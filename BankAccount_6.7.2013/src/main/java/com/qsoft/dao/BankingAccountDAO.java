package com.qsoft.dao;

import com.qsoft.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BankingAccountDAO extends JpaRepository<BankAccount, String>
{

}
