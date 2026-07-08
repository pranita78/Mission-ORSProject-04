package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.sunilos.p4.bean.SubscriptionBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class SubscriptionModel extends BaseModel<SubscriptionBean> {

    @Override
    public SubscriptionBean getBean() {
        return new SubscriptionBean();
    }

    @Override
    public long add(SubscriptionBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        SubscriptionBean existbean = findByCustomerName(bean.getCustomerName());
        if (existbean != null) {
            throw new DuplicateRecordException("customerName already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getCustomerName());
            pstmt.setString(3, bean.getPlanName());
            pstmt.setInt(4, bean.getAmount());
            if (bean.getExpiryDate() != null) {
                pstmt.setDate(5, new java.sql.Date(bean.getExpiryDate().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
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
            throw new ApplicationException("Exception : Exception in add Subscription");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model add End");
        return pk;
    }
    @Override
    public void update(SubscriptionBean bean) throws ApplicationException, DuplicateRecordException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getWhereClause(SubscriptionBean bean) {
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
                sql.append(" AND CUSTOMER_NAME like '" + bean.getCustomerName() + "%'");
            }
        }
        return sql.toString();
    }

    public SubscriptionBean findByCustomerName(String customerName) {
        return findByUniqueColumn("CUSTOMER_NAME", customerName);
    }

    @Override
    public String getTable() {
        return "st_subscription";
    }
}