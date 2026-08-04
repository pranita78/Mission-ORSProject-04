<%@page import="com.sunilos.p4.ctl.WaterMonitoringCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.WaterMonitoringBean"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.WaterMonitoringBean"
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
                <i class="bi bi-droplet-fill me-2"></i>
                <%=bean.getId() > 0 ? "Edit Water Monitoring" : "Add Water Monitoring"%>
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

            <form action="<%=ORSView.WATER_MONITORING_CTL%>" method="POST">
                <input type="hidden" name="id" value="<%=bean.getId()%>">
                <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
                <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
                <input type="hidden" name="createdDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
                <input type="hidden" name="modifiedDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-upc me-1"></i>Water Code <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="waterCode" class="form-control" maxlength="50"
                        value="<%=DataUtility.getStringData(bean.getWaterCode())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("waterCode", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-geo-alt-fill me-1"></i>Location <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="location" class="form-control" maxlength="100"
                        value="<%=DataUtility.getStringData(bean.getLocation())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("location", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-water me-1"></i>Water Level <span class="text-danger">*</span>
                    </label>
                    <select name="waterLevel" class="form-select">
                        <option value="">-- Select Water Level --</option>
                        <option value="Low" <%=("Low".equals(bean.getWaterLevel())) ? "selected" : ""%>>Low</option>
                        <option value="Normal" <%=("Normal".equals(bean.getWaterLevel())) ? "selected" : ""%>>Normal</option>
                        <option value="High" <%=("High".equals(bean.getWaterLevel())) ? "selected" : ""%>>High</option>
                        <option value="Flood" <%=("Flood".equals(bean.getWaterLevel())) ? "selected" : ""%>>Flood</option>
                    </select>
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("waterLevel", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-toggle-on me-1"></i>Status <span class="text-danger">*</span>
                    </label>
                    <select name="status" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="Active" <%=("Active".equals(bean.getStatus())) ? "selected" : ""%>>Active</option>
                        <option value="Inactive" <%=("Inactive".equals(bean.getStatus())) ? "selected" : ""%>>Inactive</option>
                        <option value="Alert" <%=("Alert".equals(bean.getStatus())) ? "selected" : ""%>>Alert</option>
                        <option value="Critical" <%=("Critical".equals(bean.getStatus())) ? "selected" : ""%>>Critical</option>
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