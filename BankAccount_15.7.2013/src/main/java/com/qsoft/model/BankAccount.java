package com.qsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/25/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "saving_account")
public class BankAccount
{
    @Id
    @Column(name = "account_no", length = 255)
    private String accountNumber;
    @Column(name = "balance")
    private double balance;
    @Column(name = "description")
    private String description;

    public BankAccount()
    {

    }
    public BankAccount(String accountNumber, double balance)
    {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
         this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
