package com.aurionpro.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
    private int transactionID;
    private String senderAccount;
    private String receiverAccount;
	private String transactionType;
    private double amount;
    private Date transactionDate;

    
    public Transaction(String senderAccount, String receiverAccount, String transactionType, double amount,
			Date transactionDate) {
		super();
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
  

	public Transaction() {
		
	}


	// Getters and Setters
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
