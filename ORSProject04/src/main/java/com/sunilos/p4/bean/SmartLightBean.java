package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmartLightBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lightCode;
	private String roomName;
	private String brightnessLevel;
	private String status;

	public String getLightCode() {
		return lightCode;
	}

	public void setLightCode(String lightCode) {
		this.lightCode = lightCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBrightnessLevel() {
		return brightnessLevel;
	}

	public void setBrightnessLevel(String brightnessLevel) {
		this.brightnessLevel = brightnessLevel;
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
		return lightCode;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setLightCode(rs.getString("LIGHT_CODE"));
			this.setRoomName(rs.getString("ROOM_NAME"));
			this.setBrightnessLevel(rs.getString("BRIGHTNESS_LEVEL"));
			this.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}