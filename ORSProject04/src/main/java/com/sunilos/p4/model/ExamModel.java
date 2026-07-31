package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ExamModel extends BaseModel<ExamBean> {

	@Override
	public ExamBean getBean() {
		return new ExamBean();
	}

	@Override
	public long add(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		ExamBean existbean = findByExamName(bean.getExamName());
		if (existbean != null) {
			throw new DuplicateRecordException("examName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getExamName());

			if (bean.getExamDate() != null) {
				pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			} else {
				pstmt.setDate(3, null);
			}

			pstmt.setString(4, bean.getTotalMarks());
			pstmt.setString(5, bean.getPassingMarks());
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
	public void update(ExamBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		ExamBean existbean = findByExamName(bean.getExamName());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("examName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET EXAM_NAME=?, EXAM_DATE=?, TOTAL_MARKS=?, PASSING_MARKS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getExamName());

			if (bean.getExamDate() != null) {
				pstmt.setDate(2, new java.sql.Date(bean.getExamDate().getTime()));
			} else {
				pstmt.setDate(2, null);
			}

			pstmt.setString(3, bean.getTotalMarks());
			pstmt.setString(4, bean.getPassingMarks());
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
	public String getWhereClause(ExamBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getExamName() != null && bean.getExamName().length() > 0) {
				sql.append(" AND EXAM_NAME like '" + bean.getExamName() + "%'");
			}
		}
		return sql.toString();
	}

	public ExamBean findByExamName(String examName) {
		return findByUniqueColumn("EXAM_NAME", examName);
	}

	@Override
	public String getTable() {
		return "st_exam";
	}
}