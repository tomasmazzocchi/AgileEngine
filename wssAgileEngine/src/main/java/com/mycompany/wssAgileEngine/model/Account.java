package com.mycompany.wssAgileEngine.model;

/**
 *
 * @author Tomas Mazzocchi
 */
public class Account {
    private Long id;
    private String fullName;
    private Float balance;

    public Account() {
    }
    
    public Account(Long id, String fullName, Float balance) {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Float getTotalAmount() {
        return balance;
    }

    public void setTotalAmount(Float balance) {
        this.balance = balance;
    }
    
    public void creditAmount(Float amount) {
        this.balance = this.balance + amount;
    }
    
    public void deditAmount(Float amount) {
        this.balance = this.balance - amount;
    }

    public boolean hasEnoughBalance(Float amount) {
        return this.balance > amount;
    }

}
