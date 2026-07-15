package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnergyConsumptionBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String energyCode;
	private String deviceName;
	private String unitsConsumed;
	private String status;

	public String getEnergyCode() {
		return energyCode;
	}

	public void setEnergyCode(String energyCode) {
		this.energyCode = energyCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(String unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
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
		return energyCode;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setEnergyCode(rs.getString("ENERGY_CODE"));
			this.setDeviceName(rs.getString("DEVICE_NAME"));
			this.setUnitsConsumed(rs.getString("UNITS_CONSUMED"));
			this.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}