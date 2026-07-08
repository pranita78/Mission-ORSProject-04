package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.SubscriptionBean;
import com.sunilos.p4.model.SubscriptionModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/SubscriptionCtl")
public class SubscriptionCtl extends BaseCtl<SubscriptionBean, SubscriptionModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("customerName"))) {
            request.setAttribute("customerName", PropertyReader.getValue("error.require", "customerName"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("planName"))) {
            request.setAttribute("planName", PropertyReader.getValue("error.require", "planName"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("amount"))) {
            request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("expiryDate"))) {
            request.setAttribute("expiryDate", PropertyReader.getValue("error.require", "expiryDate"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected SubscriptionBean populateBean(HttpServletRequest request) {
        SubscriptionBean bean = new SubscriptionBean();
        bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
        bean.setPlanName(DataUtility.getString(request.getParameter("planName")));
        bean.setAmount(DataUtility.getInt(request.getParameter("amount")));
        bean.setExpiryDate(DataUtility.getDate(request.getParameter("ExpiryDate")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.SUBSCRIPTION_VIEW;
    }

    @Override
    protected String getView(String op) {
        if (OP_CANCEL.equalsIgnoreCase(op)) {
            return ORSView.SUBSCRIPTION_CTL;
        }
        return ORSView.SUBSCRIPTION_VIEW;
    }

    @Override
    protected SubscriptionModel getModel() {
        return new SubscriptionModel();
    }
}