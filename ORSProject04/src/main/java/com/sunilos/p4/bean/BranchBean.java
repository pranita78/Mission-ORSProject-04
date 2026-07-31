package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String branchName;
	private String city;
	private String managerName;
	private String contactNo;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return branchName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setBranchName(rs.getString("BRANCH_NAME"));
			this.setCity(rs.getString("CITY"));
			this.setManagerName(rs.getString("MANAGER_NAME"));
			this.setContactNo(rs.getString("CONTACT_NO"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}