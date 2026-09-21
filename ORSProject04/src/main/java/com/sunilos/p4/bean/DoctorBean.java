package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String doctorName;
	private String specialization;
	private String experience;
	private String contactNo;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
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
		return doctorName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setDoctorName(rs.getString("DOCTOR_NAME"));
			this.setSpecialization(rs.getString("SPECIALIZATION"));
			this.setExperience(rs.getString("EXPERIENCE"));
			this.setContactNo(rs.getString("CONTACT_NO"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}