package com.qsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/25/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "transaction2")
public class TransactionDTO {
    @Id
    @Column(name = "webId")
    private Long webId;

    @Column(name = "accountNumber", length = 255)
    private String accountNumber;

    @Column(name = "timeStamp")
    private Long timeStamp;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description", length = 255)
    private String description;

//    private Calendar calendar = Calendar.getInstance() ;

    public TransactionDTO() {

    }
    public TransactionDTO(String accountNumber, long timeStamp, String deposit) {
        this.accountNumber = accountNumber;
        this.timeStamp = timeStamp;

    }

    public Long getWebId()
    {
        return webId;
    }

    public void setWebId(Long webId)
    {
        this.webId = webId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber =  accountNumber;
    }

    public void setTimeStamp(Long time) {
        this.timeStamp = time ;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

//    public void setCalendar(Calendar calendar) {
//        this.calendar = calendar;
//    }
//
//    public Calendar getCalendar() {
//        return calendar;
//    }

    public Double getAmount() {
        return amount;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
}
