package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ExamBean;
import com.sunilos.p4.model.ExamModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ExamListCtl")
public class ExamListCtl extends BaseListCtl<ExamBean, ExamModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected ExamBean populateBean(HttpServletRequest request) {
		ExamBean bean = new ExamBean();
		bean.setExamName(DataUtility.getString(request.getParameter("examName")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EXAM_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.EXAM_LIST_VIEW;
	}

	@Override
	protected ExamModel getModel() {
		return new ExamModel();
	}
}