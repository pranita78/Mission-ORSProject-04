package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FeeBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String studentId;
	private String amount;
	private Date paymentDate;
	private String paymentStatus;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return studentId;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setStudentId(rs.getString("STUDENT_ID"));
			this.setAmount(rs.getString("AMOUNT"));
			this.setPaymentDate(rs.getDate("PAYMENT_DATE"));
			this.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}