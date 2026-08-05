package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.FeeBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class FeeModel extends BaseModel<FeeBean> {

	@Override
	public FeeBean getBean() {
		return new FeeBean();
	}

	@Override
	public long add(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		FeeBean existbean = findByStudentId(bean.getStudentId());
		if (existbean != null) {
			throw new DuplicateRecordException("studentId already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getStudentId());
			pstmt.setString(3, bean.getAmount());

			if (bean.getPaymentDate() != null) {
				pstmt.setDate(4, new java.sql.Date(bean.getPaymentDate().getTime()));
			} else {
				pstmt.setDate(4, null);
			}

			pstmt.setString(5, bean.getPaymentStatus());
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
	public void update(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		FeeBean existbean = findByStudentId(bean.getStudentId());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("studentId already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET STUDENT_ID=?, AMOUNT=?, PAYMENT_DATE=?, PAYMENT_STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getStudentId());
			pstmt.setString(2, bean.getAmount());

			if (bean.getPaymentDate() != null) {
				pstmt.setDate(3, new java.sql.Date(bean.getPaymentDate().getTime()));
			} else {
				pstmt.setDate(3, null);
			}

			pstmt.setString(4, bean.getPaymentStatus());
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
	public String getWhereClause(FeeBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getStudentId() != null && bean.getStudentId().length() > 0) {
				sql.append(" AND STUDENT_ID like '" + bean.getStudentId() + "%'");
			}
		}
		return sql.toString();
	}

	public FeeBean findByStudentId(String studentId) {
		return findByUniqueColumn("STUDENT_ID", studentId);
	}

	@Override
	public String getTable() {
		return "st_fee";
	}
}