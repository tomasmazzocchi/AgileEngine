package com.mycompany.wssAgileEngine.model;

import java.util.Calendar;

/**
 *
 * @author tomas mazzocchi
 */
public class Transaction {

    private String id;
    private String type;
    private Float amount;
    private Calendar effectiveDate;

    public Transaction() {
    }

    public Transaction(String id, String type, Float amount, Calendar effectiveDate) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    }

    public Transaction(String type, Float amount, Calendar effectiveDate) {
        this.type = type;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Calendar getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Calendar effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    
}
