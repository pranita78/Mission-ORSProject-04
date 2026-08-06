package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class OrderModel extends BaseModel<OrderBean> {

	@Override
	public OrderBean getBean() {
		return new OrderBean();
	}

	@Override
	public long add(OrderBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);

			if (bean.getOrderDate() != null) {
				pstmt.setDate(2, new java.sql.Date(bean.getOrderDate().getTime()));
			} else {
				pstmt.setDate(2, null);
			}

			pstmt.setString(3, bean.getAmount());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getCustomerId());
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
	public void update(OrderBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET ORDER_DATE=?, AMOUNT=?, STATUS=?, CUSTOMER_ID=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");

			if (bean.getOrderDate() != null) {
				pstmt.setDate(1, new java.sql.Date(bean.getOrderDate().getTime()));
			} else {
				pstmt.setDate(1, null);
			}

			pstmt.setString(2, bean.getAmount());
			pstmt.setString(3, bean.getStatus());
			pstmt.setLong(4, bean.getCustomerId());
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
	public String getWhereClause(OrderBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCustomerId() > 0) {
				sql.append(" AND CUSTOMER_ID = " + bean.getCustomerId());
			}
		}
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_order";
	}
}