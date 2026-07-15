package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.VoiceCommandBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class VoiceCommandModel extends BaseModel<VoiceCommandBean> {

	@Override
	public VoiceCommandBean getBean() {
		return new VoiceCommandBean();
	}

	@Override
	public long add(VoiceCommandBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		VoiceCommandBean existbean = findByCommandCode(bean.getCommandCode());
		if (existbean != null) {
			throw new DuplicateRecordException("commandCode already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getCommandCode());
			pstmt.setString(3, bean.getUserName());
			pstmt.setString(4, bean.getCommandText());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
		    e.printStackTrace();   // Console me actual error print hoga
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
	public void update(VoiceCommandBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		VoiceCommandBean existbean = findByCommandCode(bean.getCommandCode());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("commandCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET COMMAND_CODE=?, USER_NAME=?, COMMAND_TEXT=?, STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getCommandCode());
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getCommandText());
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
	public String getWhereClause(VoiceCommandBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCommandCode() != null && bean.getCommandCode().length() > 0) {
				sql.append(" AND COMMAND_CODE like '" + bean.getCommandCode() + "%'");
			}
		}
		return sql.toString();
	}

	public VoiceCommandBean findByCommandCode(String commandCode) {
		return findByUniqueColumn("COMMAND_CODE", commandCode);
	}

	@Override
	public String getTable() {
		return "st_voice_command";
	}
}