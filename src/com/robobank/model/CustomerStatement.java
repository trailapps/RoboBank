package com.robobank.model;

import java.math.BigDecimal;

/**
 * Model class to hold customer statement information.
 * Note:: Used Plain old getters & setters instead of lombok automatic getters & setters, because review may not have activated lombok agent in their IDE
 */
public class CustomerStatement {

    private int transactionReference;
    private String accountNumber;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

    public CustomerStatement(int transactionReference, String accountNumber, String description, BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        this.transactionReference = transactionReference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }


    public int getTransactionReference() {
        return transactionReference;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    @Override
    public String toString() {
        return "CustomerStatement{" +
                "transactionReference=" + transactionReference +
                ", accountNumber='" + accountNumber + '\'' +
                ", description='" + description + '\'' +
                ", startBalance=" + startBalance +
                ", mutation=" + mutation +
                ", endBalance=" + endBalance +
                '}';
    }
}
