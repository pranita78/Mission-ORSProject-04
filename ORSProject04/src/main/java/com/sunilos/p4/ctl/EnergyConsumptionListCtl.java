package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.EnergyConsumptionBean;
import com.sunilos.p4.model.EnergyConsumptionModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/EnergyConsumptionListCtl")
public class EnergyConsumptionListCtl extends BaseListCtl<EnergyConsumptionBean, EnergyConsumptionModel> {

	private static final long serialVersionUID = 1L;

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
		return ORSView.ENERGY_CONSUMPTION_LIST_VIEW;
	}

	@Override
	protected EnergyConsumptionModel getModel() {
		return new EnergyConsumptionModel();
	}
}