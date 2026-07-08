package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BugTrackerBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private String title;
    private String severity;
    private String assignedTo;
    private String status;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String getKey() { return id + ""; }

    @Override
    public String getValue() { return title; }

    @Override
    public void setResultset(ResultSet rs) {
        try {
            super.setResultset(rs);
            this.setTitle(rs.getString("TITLE"));
            this.setSeverity(rs.getString("SEVERITY"));
            this.setAssignedTo(rs.getString("ASSIGNED_TO"));
            this.setStatus(rs.getString("STATUS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}