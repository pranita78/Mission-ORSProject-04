package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date orderDate;
	private String amount;
	private String status;
	private long customerId;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return id + "";
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setOrderDate(rs.getDate("ORDER_DATE"));
			this.setAmount(rs.getString("AMOUNT"));
			this.setStatus(rs.getString("STATUS"));
			this.setCustomerId(rs.getLong("CUSTOMER_ID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}