package com.sunilos.p4.ctl;

import java.text.SimpleDateFormat;

import com.sunilos.p4.bean.EmployeeBean;
import com.sunilos.p4.model.EmployeeModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/EmployeeCtl")
public class EmployeeCtl extends BaseCtl<EmployeeBean, EmployeeModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("designation"))) {
			request.setAttribute("designation", PropertyReader.getValue("error.require", "designation"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("salary"))) {
			request.setAttribute("salary", PropertyReader.getValue("error.require", "salary"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "joiningDate"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected EmployeeBean populateBean(HttpServletRequest request) {
		EmployeeBean bean = new EmployeeBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDesignation(DataUtility.getString(request.getParameter("designation")));
		bean.setSalary(DataUtility.getString(request.getParameter("salary")));

		String joiningDateStr = request.getParameter("joiningDate");
		if (joiningDateStr != null && !joiningDateStr.trim().isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = sdf.parse(joiningDateStr);
				bean.setJoiningDate(new java.sql.Date(parsedDate.getTime()));
			} catch (Exception e) {
				System.out.println("Date parse error for value: " + joiningDateStr);
				e.printStackTrace();
			}
		}

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.EMPLOYEE_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.EMPLOYEE_CTL;
		}
		return ORSView.EMPLOYEE_VIEW;
	}

	@Override
	protected EmployeeModel getModel() {
		return new EmployeeModel();
	}
}