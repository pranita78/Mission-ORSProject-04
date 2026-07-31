package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.WeatherAlertBean;
import com.sunilos.p4.model.WeatherAlertModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/WeatherAlertListCtl")
public class WeatherAlertListCtl extends BaseListCtl<WeatherAlertBean, WeatherAlertModel> {

	private static final long serialVersionUID = 1L;

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
		return ORSView.WEATHER_ALERT_LIST_VIEW;
	}

	@Override
	protected WeatherAlertModel getModel() {
		return new WeatherAlertModel();
	}
}