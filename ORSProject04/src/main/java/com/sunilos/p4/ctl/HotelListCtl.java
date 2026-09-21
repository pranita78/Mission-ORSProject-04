package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.model.HotelModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/HotelListCtl")
public class HotelListCtl extends BaseListCtl<HotelBean, HotelModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected HotelBean populateBean(HttpServletRequest request) {
		HotelBean bean = new HotelBean();
		bean.setHotelName(DataUtility.getString(request.getParameter("hotelName")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HOTEL_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.HOTEL_LIST_VIEW;
	}

	@Override
	protected HotelModel getModel() {
		return new HotelModel();
	}
}