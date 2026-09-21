package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.HotelBean;
import com.sunilos.p4.model.HotelModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/HotelCtl")
public class HotelCtl extends BaseCtl<HotelBean, HotelModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("hotelName"))) {
			request.setAttribute("hotelName", PropertyReader.getValue("error.require", "hotelName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "location"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("rating"))) {
			request.setAttribute("rating", PropertyReader.getValue("error.require", "rating"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "contactNo"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected HotelBean populateBean(HttpServletRequest request) {
		
		HotelBean bean = new HotelBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setHotelName(DataUtility.getString(request.getParameter("hotelName")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setRating(DataUtility.getString(request.getParameter("rating")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HOTEL_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.HOTEL_CTL;
		}
		return ORSView.HOTEL_VIEW;
	}

	@Override
	protected HotelModel getModel() {
		return new HotelModel();
	}
}