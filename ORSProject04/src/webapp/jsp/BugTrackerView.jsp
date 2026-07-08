<%@page import="com.sunilos.p4.ctl.BugTrackerCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.BugTrackerBean"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.BugTrackerBean"
    scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container py-4" style="max-width: 580px;">
    <div class="card border-0 shadow-sm rounded-4 overflow-hidden">

        <div class="card-header text-white border-0 py-3 px-4"
            style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
            <h5 class="mb-0 fw-bold">
                <i class="bi bi-bug-fill me-2"></i>
                <%=bean.getId() > 0 ? "Edit Bug" : "Add Bug"%>
            </h5>
        </div>

        <div class="card-body px-4 py-4">

            <%if (_suc != null && !_suc.isEmpty()) {%>
            <div class="alert alert-success py-2">
                <i class="bi bi-check-circle-fill me-2"></i><%=_suc%></div>
            <%}%>

            <%if (_err != null && !_err.isEmpty()) {%>
            <div class="alert alert-danger py-2">
                <i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
            <%}%>

            <form action="<%=ORSView.BUG_TRACKER_CTL%>" method="POST">
                <input type="hidden" name="id" value="<%=bean.getId()%>">
                <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
                <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
                <input type="hidden" name="createdDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
                <input type="hidden" name="modifiedDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-card-heading me-1"></i>Title <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="title" class="form-control" maxlength="200"
                        value="<%=DataUtility.getStringData(bean.getTitle())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("title", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-exclamation-triangle-fill me-1"></i>Severity <span class="text-danger">*</span>
                    </label>
                    <select name="severity" class="form-select">
                        <option value="">-- Select Severity --</option>
                        <option value="Low" <%=("Low".equals(bean.getSeverity())) ? "selected" : ""%>>Low</option>
                        <option value="Medium" <%=("Medium".equals(bean.getSeverity())) ? "selected" : ""%>>Medium</option>
                        <option value="High" <%=("High".equals(bean.getSeverity())) ? "selected" : ""%>>High</option>
                        <option value="Critical" <%=("Critical".equals(bean.getSeverity())) ? "selected" : ""%>>Critical</option>
                    </select>
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("severity", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-person-fill-gear me-1"></i>Assigned To <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="assignedTo" class="form-control" maxlength="100"
                        value="<%=DataUtility.getStringData(bean.getAssignedTo())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("assignedTo", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-toggle-on me-1"></i>Status <span class="text-danger">*</span>
                    </label>
                    <select name="status" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="Open" <%=("Open".equals(bean.getStatus())) ? "selected" : ""%>>Open</option>
                        <option value="In Progress" <%=("In Progress".equals(bean.getStatus())) ? "selected" : ""%>>In Progress</option>
                        <option value="Resolved" <%=("Resolved".equals(bean.getStatus())) ? "selected" : ""%>>Resolved</option>
                        <option value="Closed" <%=("Closed".equals(bean.getStatus())) ? "selected" : ""%>>Closed</option>
                    </select>
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("status", request)%></div>
                </div>

                <div class="d-flex gap-2 pt-2 border-top">
                    <button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
                        class="btn btn-primary">
                        <i class="bi bi-save me-1"></i> Save
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>