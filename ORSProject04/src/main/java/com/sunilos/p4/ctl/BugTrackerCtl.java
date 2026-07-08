package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.BugTrackerBean;
import com.sunilos.p4.model.BugTrackerModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BugTrackerCtl")
public class BugTrackerCtl extends BaseCtl<BugTrackerBean, BugTrackerModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("title"))) {
            request.setAttribute("title", PropertyReader.getValue("error.require", "title"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("severity"))) {
            request.setAttribute("severity", PropertyReader.getValue("error.require", "severity"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("assignedTo"))) {
            request.setAttribute("assignedTo", PropertyReader.getValue("error.require", "assignedTo"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("status"))) {
            request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BugTrackerBean populateBean(HttpServletRequest request) {
        BugTrackerBean bean = new BugTrackerBean();
        bean.setTitle(DataUtility.getString(request.getParameter("title")));
        bean.setSeverity(DataUtility.getString(request.getParameter("severity")));
        bean.setAssignedTo(DataUtility.getString(request.getParameter("assignedTo")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.BUG_TRACKER_VIEW;
    }

    @Override
    protected String getView(String op) {
        if (OP_CANCEL.equalsIgnoreCase(op)) {
            return ORSView.BUG_TRACKER_CTL;
        }
        return ORSView.BUG_TRACKER_VIEW;
    }

    @Override
    protected BugTrackerModel getModel() {
        return new BugTrackerModel();
    }
}