package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class HotelModel extends BaseModel<HotelBean> {

	@Override
	public HotelBean getBean() {
		return new HotelBean();
	}

	@Override
	public long add(HotelBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		HotelBean existbean = findByHotelName(bean.getHotelName());
		if (existbean != null) {
			throw new DuplicateRecordException("hotelName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getHotelName());
			pstmt.setString(3, bean.getLocation());
			pstmt.setString(4, bean.getRating());
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
	public void update(HotelBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		HotelBean existbean = findByHotelName(bean.getHotelName());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("hotelName already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET HOTEL_NAME=?, LOCATION=?, RATING=?, CONTACT_NO=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getHotelName());
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getRating());
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
	public String getWhereClause(HotelBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getHotelName() != null && bean.getHotelName().length() > 0) {
				sql.append(" AND HOTEL_NAME like '" + bean.getHotelName() + "%'");
			}
		}
		return sql.toString();
	}

	public HotelBean findByHotelName(String hotelName) {
		return findByUniqueColumn("HOTEL_NAME", hotelName);
	}

	@Override
	public String getTable() {
		return "st_hotel";
	}
}