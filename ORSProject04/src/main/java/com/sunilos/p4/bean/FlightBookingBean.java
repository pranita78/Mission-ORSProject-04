package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
 

public class FlightBookingBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String passengerName;
    private String flightNo;
    private String source;
    private String destination;

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getFlightNo() { return flightNo; }
    public void setFlightNo(String flightNo) { this.flightNo = flightNo; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    @Override
    public String getKey() { return id + ""; }

    @Override
    public String getValue() { return passengerName; }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);
            this.setPassengerName(rs.getString("PASSENGER_NAME"));
            this.setFlightNo(rs.getString("FLIGHT_NO"));
            this.setSource(rs.getString("SOURCE"));
            this.setDestination(rs.getString("DESTINATION"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}