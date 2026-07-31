package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.BranchBean;
import com.sunilos.p4.model.BranchModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BranchListCtl")
public class BranchListCtl extends BaseListCtl<BranchBean, BranchModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected BranchBean populateBean(HttpServletRequest request) {
		BranchBean bean = new BranchBean();
		bean.setBranchName(DataUtility.getString(request.getParameter("branchName")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.BRANCH_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.BRANCH_LIST_VIEW;
	}

	@Override
	protected BranchModel getModel() {
		return new BranchModel();
	}
}