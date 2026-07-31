package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.QRScannerBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class QRScannerModel extends BaseModel<QRScannerBean> {

	@Override
	public QRScannerBean getBean() {
		return new QRScannerBean();
	}

	@Override
	public long add(QRScannerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		QRScannerBean existbean = findByQrCode(bean.getQrCode());
		if (existbean != null) {
			throw new DuplicateRecordException("qrCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getQrCode());
			pstmt.setString(3, bean.getScannedBy());

			if (bean.getScannedTime() != null) {
				pstmt.setTimestamp(4, new java.sql.Timestamp(bean.getScannedTime().getTime()));
			} else {
				pstmt.setTimestamp(4, null);
			}

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
	public void update(QRScannerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		QRScannerBean existbean = findByQrCode(bean.getQrCode());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("qrCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET QR_CODE=?, SCANNED_BY=?, SCANNED_TIME=?, STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getQrCode());
			pstmt.setString(2, bean.getScannedBy());

			if (bean.getScannedTime() != null) {
				pstmt.setTimestamp(3, new java.sql.Timestamp(bean.getScannedTime().getTime()));
			} else {
				pstmt.setTimestamp(3, null);
			}

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
	public String getWhereClause(QRScannerBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getQrCode() != null && bean.getQrCode().length() > 0) {
				sql.append(" AND QR_CODE like '" + bean.getQrCode() + "%'");
			}
		}
		return sql.toString();
	}

	public QRScannerBean findByQrCode(String qrCode) {
		return findByUniqueColumn("QR_CODE", qrCode);
	}

	@Override
	public String getTable() {
		return "st_qr_scanner";
	}
}