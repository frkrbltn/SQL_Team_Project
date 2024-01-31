package edu.ncsu.csc440.model;

import java.sql.Date;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private Date purchaseDate;
    private double totalAmount;

    public Invoice(int invoiceId, int customerId, Date purchaseDate, double totalAmount) {
        super();
        // use setters to validate the values
        setInvoiceId(invoiceId);
        setCustomerId(customerId);
        setPurchaseDate(purchaseDate);
        setTotalAmount(totalAmount);
    }

    // these are getters
    public int getInvoiceId() {
        return invoiceId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    // Now create setters. Those values can't be null.
    private void setInvoiceId(int invoiceId) {
        // all invoice id must be greater than 0 and different from each other
        // if invoice id is less than 0, throw an exception
        if (invoiceId < 0) {
            throw new IllegalArgumentException("Invoice ID must be greater than 0");
        }
        this.invoiceId = invoiceId;
    }

    private void setCustomerId(int customerId) {
        // all customer id must be greater than 0 and different from each other
        // if customer id is less than 0, throw an exception
        if (customerId < 0) {
            throw new IllegalArgumentException("Customer ID must be greater than 0");
        }
        this.customerId = customerId;
    }

    private void setPurchaseDate(Date purchaseDate) {
        // purchase date can't be null
        if (purchaseDate == null) {
            throw new IllegalArgumentException("Purchase date can't be null");
        }
        this.purchaseDate = purchaseDate;
    }

    private void setTotalAmount(double totalAmount) {
        // total amount can't be null
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount can't be less than 0");
        }
        this.totalAmount = totalAmount;
    }
    
}
