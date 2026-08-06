package com.sunilos.p4.ctl;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.model.OrderModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/OrderCtl")
public class OrderCtl extends BaseCtl<OrderBean, OrderModel> {

	private static final long serialVersionUID = 1L;

	//private void preload(HttpServletRequest request) {
		//LinkedHashMap<String, String> statusList = new LinkedHashMap<String, String>();
		//statusList.put("PENDING", "PENDING");
		//statusList.put("DELIVERED", "DELIVERED");
		//request.setAttribute("statusList", statusList);
	//}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("orderDate"))) {
			request.setAttribute("orderDate", PropertyReader.getValue("error.require", "orderDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("customerId"))) {
			request.setAttribute("customerId", PropertyReader.getValue("error.require", "customerId"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected OrderBean populateBean(HttpServletRequest request) {
		OrderBean bean = new OrderBean();

		String orderDateStr = request.getParameter("orderDate");
		if (orderDateStr != null && !orderDateStr.trim().isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = sdf.parse(orderDateStr);
				bean.setOrderDate(new java.sql.Date(parsedDate.getTime()));
			} catch (Exception e) {
				System.out.println("Date parse error for value: " + orderDateStr);
				e.printStackTrace();
			}
		}

		bean.setAmount(DataUtility.getString(request.getParameter("amount")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		bean.setCustomerId(DataUtility.getLong(request.getParameter("customerId")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ORDER_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.ORDER_CTL;
		}
		return ORSView.ORDER_VIEW;
	}

	@Override
	protected OrderModel getModel() {
		return new OrderModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}