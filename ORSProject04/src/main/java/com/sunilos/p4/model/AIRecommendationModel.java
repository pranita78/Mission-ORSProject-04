package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.AIRecommendationBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class AIRecommendationModel extends BaseModel<AIRecommendationBean> {

	@Override
	public AIRecommendationBean getBean() {
		return new AIRecommendationBean();
	}

	@Override
	public long add(AIRecommendationBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		AIRecommendationBean existbean = findByRecommendationCode(bean.getRecommendationCode());
		if (existbean != null) {
			throw new DuplicateRecordException("recommendationCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getRecommendationCode());
			pstmt.setString(3, bean.getUserName());
			pstmt.setString(4, bean.getRecommendationType());
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
	public void update(AIRecommendationBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		AIRecommendationBean existbean = findByRecommendationCode(bean.getRecommendationCode());
		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("recommendationCode already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable()
							+ " SET RECOMMENDATION_CODE=?, USER_NAME=?, RECOMMENDATION_TYPE=?, STATUS=?, MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getRecommendationCode());
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getRecommendationType());
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
	public String getWhereClause(AIRecommendationBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getRecommendationCode() != null && bean.getRecommendationCode().length() > 0) {
				sql.append(" AND RECOMMENDATION_CODE like '" + bean.getRecommendationCode() + "%'");
			}
		}
		return sql.toString();
	}

	public AIRecommendationBean findByRecommendationCode(String recommendationCode) {
		return findByUniqueColumn("RECOMMENDATION_CODE", recommendationCode);
	}

	@Override
	public String getTable() {
		return "st_ai_recommendation";
	}
}