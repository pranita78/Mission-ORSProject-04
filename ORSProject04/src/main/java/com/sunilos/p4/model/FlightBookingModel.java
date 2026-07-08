package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.sunilos.p4.bean.FlightBookingBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class FlightBookingModel extends BaseModel<FlightBookingBean> {

    @Override
    public FlightBookingBean getBean() {
        return new FlightBookingBean();
    }

    @Override
    public long add(FlightBookingBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        FlightBookingBean existbean = findByFlightNo(bean.getFlightNo());
        if (existbean != null) {
            throw new DuplicateRecordException("flightNo already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getPassengerName());
            pstmt.setString(3, bean.getFlightNo());
            pstmt.setString(4, bean.getSource());
            pstmt.setString(5, bean.getDestination());
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
            throw new ApplicationException("Exception : Exception in add FlightBooking");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model add End");
        return pk;
    }

    @Override
    public void update(FlightBookingBean bean) throws ApplicationException, DuplicateRecordException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getWhereClause(FlightBookingBean bean) {
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getPassengerName() != null && bean.getPassengerName().length() > 0) {
                sql.append(" AND PASSENGER_NAME like '" + bean.getPassengerName() + "%'");
            }
            if (bean.getFlightNo() != null && bean.getFlightNo().length() > 0) {
                sql.append(" AND FLIGHT_NO like '" + bean.getFlightNo() + "%'");
            }
        }
        return sql.toString();
    }

    public FlightBookingBean findByFlightNo(String flightNo) {
        return findByUniqueColumn("FLIGHT_NO", flightNo);
    }

    @Override
    public String getTable() {
        return "st_flight_booking";
    }
}