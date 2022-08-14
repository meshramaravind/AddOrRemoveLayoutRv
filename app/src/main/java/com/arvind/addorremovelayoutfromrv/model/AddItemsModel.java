package com.arvind.addorremovelayoutfromrv.model;

import java.io.Serializable;

public class AddItemsModel implements Serializable {
    public int id;
    public String transactionType;
    public String title;
    public String totalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "AddItemsModel{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", title='" + title + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
