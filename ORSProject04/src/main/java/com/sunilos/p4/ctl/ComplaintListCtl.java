package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ComplaintBean;
import com.sunilos.p4.model.ComplaintModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ComplaintListCtl")
public class ComplaintListCtl extends BaseListCtl<ComplaintBean, ComplaintModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected ComplaintBean populateBean(HttpServletRequest request) {
		ComplaintBean bean = new ComplaintBean();
		bean.setComplaintCode(DataUtility.getString(request.getParameter("complaintCode")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COMPLAINT_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.COMPLAINT_LIST_VIEW;
	}

	@Override
	protected ComplaintModel getModel() {
		return new ComplaintModel();
	}
}