package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class DoctorModel extends BaseModel<DoctorBean> {

	@Override
	public DoctorBean getBean() {
		return new DoctorBean();
	}

	@Override
	public long add(DoctorBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		DoctorBean existbean = findByDoctorName(bean.getDoctorName());
		if (existbean != null) {
			throw new DuplicateRecordException("doctorName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getDoctorName());
			pstmt.setString(3, bean.getSpecialization());
			pstmt.setString(4, bean.getExperience());
			pstmt.setString(5, bean.getContactNo());
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
	public void update(DoctorBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		DoctorBean existbean = findByDoctorName(bean.getDoctorName());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("doctorName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET DOCTOR_NAME=?, SPECIALIZATION=?, EXPERIENCE=?, CONTACT_NO=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getDoctorName());
			pstmt.setString(2, bean.getSpecialization());
			pstmt.setString(3, bean.getExperience());
			pstmt.setString(4, bean.getContactNo());
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
	public String getWhereClause(DoctorBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				sql.append(" AND DOCTOR_NAME like '" + bean.getDoctorName() + "%'");
			}
		}
		return sql.toString();
	}

	public DoctorBean findByDoctorName(String doctorName) {
		return findByUniqueColumn("DOCTOR_NAME", doctorName);
	}

	@Override
	public String getTable() {
		return "st_doctor";
	}
}