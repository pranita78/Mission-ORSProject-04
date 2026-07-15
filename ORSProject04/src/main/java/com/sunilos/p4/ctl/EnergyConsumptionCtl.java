package com.sunilos.p4.ctl;

import java.util.LinkedHashMap;

import com.sunilos.p4.bean.EnergyConsumptionBean;
import com.sunilos.p4.model.EnergyConsumptionModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/EnergyConsumptionCtl")
public class EnergyConsumptionCtl extends BaseCtl<EnergyConsumptionBean, EnergyConsumptionModel> {

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

		if (DataValidator.isNull(request.getParameter("energyCode"))) {
			request.setAttribute("energyCode", PropertyReader.getValue("error.require", "energyCode"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("deviceName"))) {
			request.setAttribute("deviceName", PropertyReader.getValue("error.require", "deviceName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("unitsConsumed"))) {
			request.setAttribute("unitsConsumed", PropertyReader.getValue("error.require", "unitsConsumed"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected EnergyConsumptionBean populateBean(HttpServletRequest request) {
		EnergyConsumptionBean bean = new EnergyConsumptionBean();
		bean.setEnergyCode(DataUtility.getString(request.getParameter("energyCode")));
		bean.setDeviceName(DataUtility.getString(request.getParameter("deviceName")));
		bean.setUnitsConsumed(DataUtility.getString(request.getParameter("unitsConsumed")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ENERGY_CONSUMPTION_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.ENERGY_CONSUMPTION_CTL;
		}
		return ORSView.ENERGY_CONSUMPTION_VIEW;
	}

	@Override
	protected EnergyConsumptionModel getModel() {
		return new EnergyConsumptionModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}