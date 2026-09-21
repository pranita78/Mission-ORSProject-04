package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hotelName;
	private String location;
	private String rating;
	private String contactNo;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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
		return hotelName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setHotelName(rs.getString("HOTEL_NAME"));
			this.setLocation(rs.getString("LOCATION"));
			this.setRating(rs.getString("RATING"));
			this.setContactNo(rs.getString("CONTACT_NO"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}