package com.mycompany.wssAgileEngine.requestBodies;

/**
 *
 * @author tomas mazzocchi
 */
public class TransactionRequestBody {
    private String accountId;
    private String type;
    private Double amount;

    public TransactionRequestBody() {
    }

    public TransactionRequestBody(String accountId, String type, Double amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
