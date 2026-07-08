package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WaterMonitoringBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String waterCode;
    private String location;
    private String waterLevel;
    private String status;

    public String getWaterCode() { return waterCode; }
    public void setWaterCode(String waterCode) { this.waterCode = waterCode; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getWaterLevel() { return waterLevel; }
    public void setWaterLevel(String waterLevel) { this.waterLevel = waterLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String getKey() { return id + ""; }

    @Override
    public String getValue() { return waterCode; }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);
            this.setWaterCode(rs.getString("WATER_CODE"));
            this.setLocation(rs.getString("LOCATION"));
            this.setWaterLevel(rs.getString("WATER_LEVEL"));
            this.setStatus(rs.getString("STATUS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}