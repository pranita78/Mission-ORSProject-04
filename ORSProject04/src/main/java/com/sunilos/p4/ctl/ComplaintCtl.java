package com.sunilos.p4.ctl;

import java.util.LinkedHashMap;

import com.sunilos.p4.bean.ComplaintBean;
import com.sunilos.p4.model.ComplaintModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ComplaintCtl")
public class ComplaintCtl extends BaseCtl<ComplaintBean, ComplaintModel> {

	private static final long serialVersionUID = 1L;

	//private void preload(HttpServletRequest request) {
		//LinkedHashMap<String, String> statusList = new LinkedHashMap<String, String>();
		//statusList.put("OPEN", "OPEN");
		//statusList.put("RESOLVED", "RESOLVED");
		//request.setAttribute("statusList", statusList);
	//}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("complaintCode"))) {
			request.setAttribute("complaintCode", PropertyReader.getValue("error.require", "complaintCode"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("customerName"))) {
			request.setAttribute("customerName", PropertyReader.getValue("error.require", "customerName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("complaintType"))) {
			request.setAttribute("complaintType", PropertyReader.getValue("error.require", "complaintType"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected ComplaintBean populateBean(HttpServletRequest request) {
		ComplaintBean bean = new ComplaintBean();
		bean.setComplaintCode(DataUtility.getString(request.getParameter("complaintCode")));
		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
		bean.setComplaintType(DataUtility.getString(request.getParameter("complaintType")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COMPLAINT_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.COMPLAINT_CTL;
		}
		return ORSView.COMPLAINT_VIEW;
	}

	@Override
	protected ComplaintModel getModel() {
		return new ComplaintModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}