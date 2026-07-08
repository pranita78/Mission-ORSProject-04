package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.sunilos.p4.bean.BugTrackerBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class BugTrackerModel extends BaseModel<BugTrackerBean> {

    @Override
    public BugTrackerBean getBean() {
        return new BugTrackerBean();
    }

    @Override
    public long add(BugTrackerBean bean) throws ApplicationException, DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        BugTrackerBean existbean = findByTitle(bean.getTitle());
        if (existbean != null) {
            throw new DuplicateRecordException("title already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO " + getTable() + 
                " (ID, TITLE, SEVERITY, ASSIGNED_TO, STATUS, CREATED_BY, MODIFIED_BY, CREATED_DATETIME, MODIFIED_DATETIME)" +
                " VALUES(?,?,?,?,?,?,?,NOW(),NOW())");
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getTitle());
            pstmt.setString(3, bean.getSeverity());
            pstmt.setString(4, bean.getAssignedTo());
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
            throw new ApplicationException("Exception : Exception in add BugTracker");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model add End");
        return pk;
    }
    @Override
    public void update(BugTrackerBean bean) throws ApplicationException, DuplicateRecordException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getWhereClause(BugTrackerBean bean) {
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getTitle() != null && bean.getTitle().length() > 0) {
                sql.append(" AND TITLE like '" + bean.getTitle() + "%'");
            }
            if (bean.getStatus() != null && bean.getStatus().length() > 0) {
                sql.append(" AND STATUS like '" + bean.getStatus() + "%'");
            }
        }
        return sql.toString();
    }

    public BugTrackerBean findByTitle(String title) {
        return findByUniqueColumn("TITLE", title);
    }

    @Override
    public String getTable() {
        return "st_bug_tracker";
    }
}