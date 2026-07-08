package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class SubscriptionBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String customerName;
    private String planName;
    private int amount;
    private Date expiryDate;

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String getKey() {
        return id + "";
    }

    @Override
    public String getValue() {
        return customerName;
    }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);
            this.setCustomerName(rs.getString("CUSTOMER_NAME"));
            this.setPlanName(rs.getString("PLAN_NAME"));
            this.setAmount(rs.getInt("AMOUNT"));
            this.setExpiryDate(rs.getDate("EXPIRY_DATE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}