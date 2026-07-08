package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.BugTrackerBean;
import com.sunilos.p4.model.BugTrackerModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/BugTrackerListCtl")
public class BugTrackerListCtl extends BaseListCtl<BugTrackerBean, BugTrackerModel> {

    private static final long serialVersionUID = 1L;

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
        return ORSView.BUG_TRACKER_LIST_VIEW;
    }

    @Override
    protected BugTrackerModel getModel() {
        return new BugTrackerModel();
    }
}