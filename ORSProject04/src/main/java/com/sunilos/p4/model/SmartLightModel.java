package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.SmartLightBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class SmartLightModel extends BaseModel<SmartLightBean> {

	@Override
	public SmartLightBean getBean() {
		return new SmartLightBean();
	}

	@Override
	public long add(SmartLightBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		SmartLightBean existbean = findByLightCode(bean.getLightCode());
		if (existbean != null) {
			throw new DuplicateRecordException("lightCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");

			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getLightCode());
			pstmt.setString(3, bean.getRoomName());
			pstmt.setString(4, bean.getBrightnessLevel());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(SmartLightBean bean) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
	}

	@Override
	public String getWhereClause(SmartLightBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getLightCode() != null && bean.getLightCode().length() > 0) {
				sql.append(" AND LIGHT_CODE like '" + bean.getLightCode() + "%'");
			}
		}
		return sql.toString();
	}

	public SmartLightBean findByLightCode(String lightCode) {
		return findByUniqueColumn("LIGHT_CODE", lightCode);
	}

	@Override
	public String getTable() {
		return "st_smart_light";
	}
}