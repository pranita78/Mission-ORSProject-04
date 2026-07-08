package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.sunilos.p4.bean.WaterMonitoringBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class WaterMonitoringModel extends BaseModel<WaterMonitoringBean> {

    @Override
    public WaterMonitoringBean getBean() {
        return new WaterMonitoringBean();
    }

    @Override
    public long add(WaterMonitoringBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        WaterMonitoringBean existbean = findByWaterCode(bean.getWaterCode());
        if (existbean != null) {
            throw new DuplicateRecordException("waterCode already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getWaterCode());
            pstmt.setString(3, bean.getLocation());
            pstmt.setString(4, bean.getWaterLevel());
            pstmt.setString(5, bean.getStatus());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add WaterMonitoring");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model add End");
        return pk;
    }

    @Override
    public void update(WaterMonitoringBean bean) throws ApplicationException, DuplicateRecordException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getWhereClause(WaterMonitoringBean bean) {
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getWaterCode() != null && bean.getWaterCode().length() > 0) {
                sql.append(" AND WATER_CODE like '" + bean.getWaterCode() + "%'");
            }
            if (bean.getLocation() != null && bean.getLocation().length() > 0) {
                sql.append(" AND LOCATION like '" + bean.getLocation() + "%'");
            }
        }
        return sql.toString();
    }

    public WaterMonitoringBean findByWaterCode(String waterCode) {
        return findByUniqueColumn("WATER_CODE", waterCode);
    }

    @Override
    public String getTable() {
        return "st_water_monitoring";
    }
}