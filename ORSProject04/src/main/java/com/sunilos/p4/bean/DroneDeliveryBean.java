package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DroneDeliveryBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String droneCode;
    private String operatorName;
    private String deliveryZone;
    private String status;

    public String getDroneCode() { return droneCode; }
    public void setDroneCode(String droneCode) { this.droneCode = droneCode; }

    public String getOperatorName() { return operatorName; }
    public void setOperatorName(String operatorName) { this.operatorName = operatorName; }

    public String getDeliveryZone() { return deliveryZone; }
    public void setDeliveryZone(String deliveryZone) { this.deliveryZone = deliveryZone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String getKey() { return id + ""; }

    @Override
    public String getValue() { return droneCode; }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);
            this.setDroneCode(rs.getString("DRONE_CODE"));
            this.setOperatorName(rs.getString("OPERATOR_NAME"));
            this.setDeliveryZone(rs.getString("DELIVERY_ZONE"));
            this.setStatus(rs.getString("STATUS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}