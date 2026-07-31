package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ExamBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String examName;
	private Date examDate;
	private String totalMarks;
	private String passingMarks;

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getPassingMarks() {
		return passingMarks;
	}

	public void setPassingMarks(String passingMarks) {
		this.passingMarks = passingMarks;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return examName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setExamName(rs.getString("EXAM_NAME"));
			this.setExamDate(rs.getDate("EXAM_DATE"));
			this.setTotalMarks(rs.getString("TOTAL_MARKS"));
			this.setPassingMarks(rs.getString("PASSING_MARKS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}