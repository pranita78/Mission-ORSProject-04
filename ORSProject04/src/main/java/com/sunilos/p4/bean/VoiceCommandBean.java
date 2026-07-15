package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoiceCommandBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String commandCode;
	private String userName;
	private String commandText;
	private String status;

	public String getCommandCode() {
		return commandCode;
	}
	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommandText() {
		return commandText;
	}
	public void setCommandText(String commandText) {
		this.commandText = commandText;
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
		return commandCode;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setCommandCode(rs.getString("COMMAND_CODE"));
			this.setUserName(rs.getString("USER_NAME"));
			this.setCommandText(rs.getString("COMMAND_TEXT"));
			this.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}