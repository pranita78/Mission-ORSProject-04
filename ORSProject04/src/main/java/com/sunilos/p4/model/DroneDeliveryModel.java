package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.sunilos.p4.bean.DroneDeliveryBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class DroneDeliveryModel extends BaseModel<DroneDeliveryBean> {

    @Override
    public DroneDeliveryBean getBean() {
        return new DroneDeliveryBean();
    }

    @Override
    public long add(DroneDeliveryBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        DroneDeliveryBean existbean = findByDroneCode(bean.getDroneCode());
        if (existbean != null) {
            throw new DuplicateRecordException("drone code already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO " + getTable() +
                " (ID, DRONE_CODE, OPERATOR_NAME, DELIVERY_ZONE, STATUS, CREATED_BY, MODIFIED_BY, CREATED_DATETIME, MODIFIED_DATETIME)" +
                " VALUES(?,?,?,?,?,?,?,NOW(),NOW())");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getDroneCode());
            pstmt.setString(3, bean.getOperatorName());
            pstmt.setString(4, bean.getDeliveryZone());
            pstmt.setString(5, bean.getStatus());
            pstmt.setLong(6, 1);  // createdBy default ID
            pstmt.setLong(7, 1);  // modifiedBy default ID
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
            throw new ApplicationException("Exception : Exception in add DroneDelivery");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model add End");
        return pk;
    }

    @Override
    public void update(DroneDeliveryBean bean) throws ApplicationException, DuplicateRecordException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getWhereClause(DroneDeliveryBean bean) {
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getDroneCode() != null && bean.getDroneCode().length() > 0) {
                sql.append(" AND DRONE_CODE like '" + bean.getDroneCode() + "%'");
            }
            if (bean.getStatus() != null && bean.getStatus().length() > 0) {
                sql.append(" AND STATUS like '" + bean.getStatus() + "%'");
            }
        }
        return sql.toString();
    }

    public DroneDeliveryBean findByDroneCode(String droneCode) {
        return findByUniqueColumn("DRONE_CODE", droneCode);
    }

    @Override
    public String getTable() {
        return "st_drone_delivery";
    }
}