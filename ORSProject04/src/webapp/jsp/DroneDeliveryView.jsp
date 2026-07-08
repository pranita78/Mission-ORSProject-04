<%@page import="com.sunilos.p4.ctl.DroneDeliveryCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.DroneDeliveryBean"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.DroneDeliveryBean"
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
                <i class="bi bi-send-fill me-2"></i>
                <%=bean.getId() > 0 ? "Edit Drone Delivery" : "Add Drone Delivery"%>
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

            <form action="<%=ORSView.DRONE_DELIVERY_CTL%>" method="POST">
                <input type="hidden" name="id" value="<%=bean.getId()%>">
                <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
                <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
                <input type="hidden" name="createdDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
                <input type="hidden" name="modifiedDatetime"
                    value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-upc-scan me-1"></i>Drone Code <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="droneCode" class="form-control" maxlength="50"
                        value="<%=DataUtility.getStringData(bean.getDroneCode())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("droneCode", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-person-fill-gear me-1"></i>Operator Name <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="operatorName" class="form-control" maxlength="100"
                        value="<%=DataUtility.getStringData(bean.getOperatorName())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("operatorName", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-geo-alt-fill me-1"></i>Delivery Zone <span class="text-danger">*</span>
                    </label>
                    <input type="text" name="deliveryZone" class="form-control" maxlength="100"
                        value="<%=DataUtility.getStringData(bean.getDeliveryZone())%>">
                    <div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("deliveryZone", request)%></div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">
                        <i class="bi bi-toggle-on me-1"></i>Status <span class="text-danger">*</span>
                    </label>
                    <select name="status" class="form-select">
                        <option value="">-- Select Status --</option>
                        <option value="Scheduled" <%=("Scheduled".equals(bean.getStatus())) ? "selected" : ""%>>Scheduled</option>
                        <option value="In Transit" <%=("In Transit".equals(bean.getStatus())) ? "selected" : ""%>>In Transit</option>
                        <option value="Delivered" <%=("Delivered".equals(bean.getStatus())) ? "selected" : ""%>>Delivered</option>
                        <option value="Cancelled" <%=("Cancelled".equals(bean.getStatus())) ? "selected" : ""%>>Cancelled</option>
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