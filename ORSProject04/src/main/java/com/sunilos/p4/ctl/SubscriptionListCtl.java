package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.SubscriptionBean;
import com.sunilos.p4.model.SubscriptionModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/SubscriptionListCtl")
public class SubscriptionListCtl extends BaseListCtl<SubscriptionBean, SubscriptionModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected SubscriptionBean populateBean(HttpServletRequest request) {
        SubscriptionBean bean = new SubscriptionBean();
        bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
        bean.setPlanName(DataUtility.getString(request.getParameter("planName")));
        bean.setAmount(DataUtility.getInt(request.getParameter("amount")));
        bean.setExpiryDate(DataUtility.getDate(request.getParameter("Date")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.SUBSCRIPTION_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.SUBSCRIPTION_LIST_VIEW;
    }

    @Override
    protected SubscriptionModel getModel() {
        return new SubscriptionModel();
    }
}