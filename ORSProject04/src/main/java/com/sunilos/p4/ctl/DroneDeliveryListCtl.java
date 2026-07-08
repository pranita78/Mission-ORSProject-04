package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.DroneDeliveryBean;
import com.sunilos.p4.model.DroneDeliveryModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/DroneDeliveryListCtl")
public class DroneDeliveryListCtl extends BaseListCtl<DroneDeliveryBean, DroneDeliveryModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected DroneDeliveryBean populateBean(HttpServletRequest request) {
        DroneDeliveryBean bean = new DroneDeliveryBean();
        bean.setDroneCode(DataUtility.getString(request.getParameter("droneCode")));
        bean.setOperatorName(DataUtility.getString(request.getParameter("operatorName")));
        bean.setDeliveryZone(DataUtility.getString(request.getParameter("deliveryZone")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.DRONE_DELIVERY_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.DRONE_DELIVERY_LIST_VIEW;
    }

    @Override
    protected DroneDeliveryModel getModel() {
        return new DroneDeliveryModel();
    }
}