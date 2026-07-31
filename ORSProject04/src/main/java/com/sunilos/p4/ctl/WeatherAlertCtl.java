package com.sunilos.p4.ctl;

import java.util.LinkedHashMap;

import com.sunilos.p4.bean.WeatherAlertBean;
import com.sunilos.p4.model.WeatherAlertModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/WeatherAlertCtl")
public class WeatherAlertCtl extends BaseCtl<WeatherAlertBean, WeatherAlertModel> {

	private static final long serialVersionUID = 1L;

	//private void preload(HttpServletRequest request) {
		//LinkedHashMap<String, String> statusList = new LinkedHashMap<String, String>();
		//statusList.put("ON", "ON");
		//statusList.put("OFF", "OFF");
		//request.setAttribute("statusList", statusList);
	//}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("alertCode"))) {
			request.setAttribute("alertCode", PropertyReader.getValue("error.require", "alertCode"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cityName"))) {
			request.setAttribute("cityName", PropertyReader.getValue("error.require", "cityName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("temperature"))) {
			request.setAttribute("temperature", PropertyReader.getValue("error.require", "temperature"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected WeatherAlertBean populateBean(HttpServletRequest request) {
		WeatherAlertBean bean = new WeatherAlertBean();
		bean.setAlertCode(DataUtility.getString(request.getParameter("alertCode")));
		bean.setCityName(DataUtility.getString(request.getParameter("cityName")));
		bean.setTemperature(DataUtility.getString(request.getParameter("temperature")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.WEATHER_ALERT_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.WEATHER_ALERT_CTL;
		}
		return ORSView.WEATHER_ALERT_VIEW;
	}

	@Override
	protected WeatherAlertModel getModel() {
		return new WeatherAlertModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}