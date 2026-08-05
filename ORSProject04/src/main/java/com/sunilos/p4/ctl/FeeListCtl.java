package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.FeeBean;
import com.sunilos.p4.model.FeeModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FeeListCtl")
public class FeeListCtl extends BaseListCtl<FeeBean, FeeModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected FeeBean populateBean(HttpServletRequest request) {
		FeeBean bean = new FeeBean();
		bean.setStudentId(DataUtility.getString(request.getParameter("studentId")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FEE_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.FEE_LIST_VIEW;
	}

	@Override
	protected FeeModel getModel() {
		return new FeeModel();
	}
}