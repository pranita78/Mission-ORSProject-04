package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.OrderBean;
import com.sunilos.p4.model.OrderModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/OrderListCtl")
public class OrderListCtl extends BaseListCtl<OrderBean, OrderModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected OrderBean populateBean(HttpServletRequest request) {
		OrderBean bean = new OrderBean();
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
		return ORSView.ORDER_LIST_VIEW;
	}

	@Override
	protected OrderModel getModel() {
		return new OrderModel();
	}
}