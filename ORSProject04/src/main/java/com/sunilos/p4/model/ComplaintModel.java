package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.ComplaintBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ComplaintModel extends BaseModel<ComplaintBean> {

	@Override
	public ComplaintBean getBean() {
		return new ComplaintBean();
	}

	@Override
	public long add(ComplaintBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		ComplaintBean existbean = findByComplaintCode(bean.getComplaintCode());
		if (existbean != null) {
			throw new DuplicateRecordException("complaintCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getComplaintCode());
			pstmt.setString(3, bean.getCustomerName());
			pstmt.setString(4, bean.getComplaintType());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
		    e.printStackTrace();
		    try {
		        if (conn != null) {
		            conn.rollback();
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    throw new ApplicationException("Exception : " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(ComplaintBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		ComplaintBean existbean = findByComplaintCode(bean.getComplaintCode());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("complaintCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET COMPLAINT_CODE=?, CUSTOMER_NAME=?, COMPLAINT_TYPE=?, STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getComplaintCode());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getComplaintType());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			throw new ApplicationException("Exception : " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	@Override
	public String getWhereClause(ComplaintBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getComplaintCode() != null && bean.getComplaintCode().length() > 0) {
				sql.append(" AND COMPLAINT_CODE like '" + bean.getComplaintCode() + "%'");
			}
		}
		return sql.toString();
	}

	public ComplaintBean findByComplaintCode(String complaintCode) {
		return findByUniqueColumn("COMPLAINT_CODE", complaintCode);
	}

	@Override
	public String getTable() {
		return "st_complaint";
	}
}