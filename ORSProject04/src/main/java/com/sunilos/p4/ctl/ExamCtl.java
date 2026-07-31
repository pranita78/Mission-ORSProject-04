package com.sunilos.p4.ctl;

import java.text.SimpleDateFormat;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.model.ExamModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ExamCtl")
public class ExamCtl extends BaseCtl<ExamBean, ExamModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("examName"))) {
			request.setAttribute("examName", PropertyReader.getValue("error.require", "examName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "examDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("totalMarks"))) {
			request.setAttribute("totalMarks", PropertyReader.getValue("error.require", "totalMarks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("passingMarks"))) {
			request.setAttribute("passingMarks", PropertyReader.getValue("error.require", "passingMarks"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected ExamBean populateBean(HttpServletRequest request) {
		ExamBean bean = new ExamBean();
		bean.setExamName(DataUtility.getString(request.getParameter("examName")));

		String examDateStr = request.getParameter("examDate");
		if (examDateStr != null && !examDateStr.trim().isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = sdf.parse(examDateStr);
				bean.setExamDate(new java.sql.Date(parsedDate.getTime()));
			} catch (Exception e) {
				System.out.println("Date parse error for value: " + examDateStr);
				e.printStackTrace();
			}
		}

		bean.setTotalMarks(DataUtility.getString(request.getParameter("totalMarks")));
		bean.setPassingMarks(DataUtility.getString(request.getParameter("passingMarks")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EXAM_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.EXAM_CTL;
		}
		return ORSView.EXAM_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}
}