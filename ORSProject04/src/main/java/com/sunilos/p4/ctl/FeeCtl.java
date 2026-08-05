package com.sunilos.p4.ctl;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

import com.sunilos.p4.bean.FeeBean;
import com.sunilos.p4.model.FeeModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FeeCtl")
public class FeeCtl extends BaseCtl<FeeBean, FeeModel> {

	private static final long serialVersionUID = 1L;

	//private void preload(HttpServletRequest request) {
		//LinkedHashMap<String, String> statusList = new LinkedHashMap<String, String>();
		//statusList.put("PAID", "PAID");
		//statusList.put("PENDING", "PENDING");
		//request.setAttribute("statusList", statusList);
	//}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "studentId"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentDate"))) {
			request.setAttribute("paymentDate", PropertyReader.getValue("error.require", "paymentDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("paymentStatus"))) {
			request.setAttribute("paymentStatus", PropertyReader.getValue("error.require", "paymentStatus"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected FeeBean populateBean(HttpServletRequest request) {
		FeeBean bean = new FeeBean();
		bean.setStudentId(DataUtility.getString(request.getParameter("studentId")));
		bean.setAmount(DataUtility.getString(request.getParameter("amount")));

		String paymentDateStr = request.getParameter("paymentDate");
		if (paymentDateStr != null && !paymentDateStr.trim().isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = sdf.parse(paymentDateStr);
				bean.setPaymentDate(new java.sql.Date(parsedDate.getTime()));
			} catch (Exception e) {
				System.out.println("Date parse error for value: " + paymentDateStr);
				e.printStackTrace();
			}
		}

		bean.setPaymentStatus(DataUtility.getString(request.getParameter("paymentStatus")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.FEE_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.FEE_CTL;
		}
		return ORSView.FEE_VIEW;
	}

	@Override
	protected FeeModel getModel() {
		return new FeeModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}