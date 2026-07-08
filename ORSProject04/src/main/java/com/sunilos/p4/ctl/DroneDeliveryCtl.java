package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.DroneDeliveryBean;
import com.sunilos.p4.model.DroneDeliveryModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/DroneDeliveryCtl")
public class DroneDeliveryCtl extends BaseCtl<DroneDeliveryBean, DroneDeliveryModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("droneCode"))) {
            request.setAttribute("droneCode", PropertyReader.getValue("error.require", "droneCode"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("operatorName"))) {
            request.setAttribute("operatorName", PropertyReader.getValue("error.require", "operatorName"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("deliveryZone"))) {
            request.setAttribute("deliveryZone", PropertyReader.getValue("error.require", "deliveryZone"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("status"))) {
            request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
            pass = false;
        }

        return pass;
    }

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
        if (OP_CANCEL.equalsIgnoreCase(op)) {
            return ORSView.DRONE_DELIVERY_CTL;
        }
        return ORSView.DRONE_DELIVERY_VIEW;
    }

    @Override
    protected DroneDeliveryModel getModel() {
        return new DroneDeliveryModel();
    }
}