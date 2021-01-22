package com.mycompany.wssAgileEngine.dtos;

import java.util.Calendar;

/**
 *
 * @author tomas mazzocchi
 */
public class TransactionDTO {

    private String id;
    private String type;
    private Double amount;
    private Calendar effectiveDate;

    public TransactionDTO(String id, String type, Double amount, Calendar effectiveDate) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    } 

    public TransactionDTO() {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Calendar getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Calendar effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    
}
