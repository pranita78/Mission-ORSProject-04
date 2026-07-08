package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.WaterMonitoringBean;
import com.sunilos.p4.model.WaterMonitoringModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/WaterMonitoringListCtl")
public class WaterMonitoringListCtl extends BaseListCtl<WaterMonitoringBean, WaterMonitoringModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected WaterMonitoringBean populateBean(HttpServletRequest request) {
        WaterMonitoringBean bean = new WaterMonitoringBean();
        bean.setWaterCode(DataUtility.getString(request.getParameter("waterCode")));
        bean.setLocation(DataUtility.getString(request.getParameter("location")));
        bean.setWaterLevel(DataUtility.getString(request.getParameter("waterLevel")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.WATER_MONITORING_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.WATER_MONITORING_LIST_VIEW;
    }

    @Override
    protected WaterMonitoringModel getModel() {
        return new WaterMonitoringModel();
    }
}