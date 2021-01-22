package com.mycompany.wssAgileEngine.dtos;

/**
 *
 * @author Tomas Mazzocchi
 */
public class AccountDTO {
    private Long id;
    private String fullName;
    private Double balance;

    public AccountDTO() {
    }

    public AccountDTO(Long id, String fullName, Double balance) {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
