package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BranchCtl")
public class BranchCtl extends BaseCtl<BranchBean, BranchModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("branchName"))) {
			request.setAttribute("branchName", PropertyReader.getValue("error.require", "branchName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "city"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("managerName"))) {
			request.setAttribute("managerName", PropertyReader.getValue("error.require", "managerName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "contactNo"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BranchBean populateBean(HttpServletRequest request) {
		BranchBean bean = new BranchBean();
		bean.setBranchName(DataUtility.getString(request.getParameter("branchName")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setManagerName(DataUtility.getString(request.getParameter("managerName")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.BRANCH_CTL;
		}
		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected BranchModel getModel() {
		return new BranchModel();
	}
}