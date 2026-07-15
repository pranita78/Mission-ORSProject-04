package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AIRecommendationBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recommendationCode;
	private String userName;
	private String recommendationType;
	private String status;

	public String getRecommendationCode() {
		return recommendationCode;
	}

	public void setRecommendationCode(String recommendationCode) {
		this.recommendationCode = recommendationCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecommendationType() {
		return recommendationType;
	}

	public void setRecommendationType(String recommendationType) {
		this.recommendationType = recommendationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return recommendationCode;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setRecommendationCode(rs.getString("RECOMMENDATION_CODE"));
			this.setUserName(rs.getString("USER_NAME"));
			this.setRecommendationType(rs.getString("RECOMMENDATION_TYPE"));
			this.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}