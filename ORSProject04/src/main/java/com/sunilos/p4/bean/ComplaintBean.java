package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String complaintCode;
	private String customerName;
	private String complaintType;
	private String status;

	public String getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(String complaintCode) {
		this.complaintCode = complaintCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return complaintCode;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setComplaintCode(rs.getString("COMPLAINT_CODE"));
			this.setCustomerName(rs.getString("CUSTOMER_NAME"));
			this.setComplaintType(rs.getString("COMPLAINT_TYPE"));
			this.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}