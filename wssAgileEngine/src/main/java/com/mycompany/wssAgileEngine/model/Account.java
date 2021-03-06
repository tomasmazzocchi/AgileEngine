package com.mycompany.wssAgileEngine.model;

/**
 *
 * @author Tomas Mazzocchi
 */
public class Account {
    private Long id;
    private String fullName;
    private Double balance;

    public Account() {
    }
    
    public Account(Long id, String fullName, Double balance) {
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

    public Double getTotalAmount() {
        return balance;
    }

    public void setTotalAmount(Double balance) {
        this.balance = balance;
    }
    
    public void creditAmount(Double amount) {
        this.balance = this.balance + amount;
    }
    
    public void deditAmount(Double amount) {
        this.balance = this.balance - amount;
    }

    public boolean hasEnoughBalance(Double amount) {
        return this.balance > amount;
    }

}
