<%@page import="com.sunilos.p4.bean.WaterMonitoringBean"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;
List list = ServletUtility.getList(request);
Iterator<WaterMonitoringBean> it = list.iterator();
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 1000px;">
    <div class="card border-0 shadow-sm rounded-4 overflow-hidden">

        <div class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
            style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
            <h5 class="mb-0 fw-bold">
                <i class="bi bi-droplet me-2"></i> Water Monitoring List
            </h5>
            <div class="d-flex gap-2">
                <a href="#" target="_blank" class="btn btn-sm btn-warning fw-semibold">
                    <i class="bi bi-file-earmark-pdf me-1"></i> Print PDF
                </a>
                <a href="#?type=doc" target="_blank" class="btn btn-sm btn-info fw-semibold">
                    <i class="bi bi-file-earmark-word me-1"></i> Print DOC
                </a>
                <a href="<%=ORSView.WATER_MONITORING_CTL%>" class="btn btn-sm btn-light text-primary fw-semibold">
                    <i class="bi bi-droplet-fill me-1"></i> Add Water Monitoring
                </a>
            </div>
        </div>

        <form action="<%=ORSView.WATER_MONITORING_LIST_CTL%>" method="POST">
            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">

            <div class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">
                <input type="text" name="waterCode"
                    class="form-control form-control-sm" style="max-width: 180px;"
                    placeholder="Search by Water Code"
                    value="<%=ServletUtility.getParameter("waterCode", request)%>">
                <input type="text" name="location"
                    class="form-control form-control-sm" style="max-width: 180px;"
                    placeholder="Search by Location"
                    value="<%=ServletUtility.getParameter("location", request)%>">
                <button type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"
                    class="btn btn-primary btn-sm">
                    <i class="bi bi-search me-1"></i> Search
                </button>
                <button type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>"
                    class="btn btn-danger btn-sm ms-auto">
                    <i class="bi bi-trash me-1"></i> Delete Selected
                </button>
            </div>

            <%if (_err != null && !_err.isEmpty()) {%>
            <div class="alert alert-danger py-2 mx-3 mt-3">
                <i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
            <%}%>

            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-dark">
                        <tr>
                            <th width="40"><input type="checkbox"
                                onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)"></th>
                            <th>S No.</th>
                            <th>Water Code</th>
                            <th>Location</th>
                            <th>Water Level</th>
                            <th>Status</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%while (it.hasNext()) {
                            WaterMonitoringBean bean = it.next();%>
                        <tr>
                            <td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                            <td class="text-muted small"><%=index++%></td>
                            <td class="fw-semibold"><%=bean.getWaterCode()%></td>
                            <td><%=bean.getLocation()%></td>
                            <td>
                                <%
                                String wl = bean.getWaterLevel();
                                String wlBadge = "secondary";
                                if ("Flood".equals(wl)) wlBadge = "danger";
                                else if ("High".equals(wl)) wlBadge = "warning";
                                else if ("Normal".equals(wl)) wlBadge = "success";
                                else if ("Low".equals(wl)) wlBadge = "info";
                                %>
                                <span class="badge bg-<%=wlBadge%>"><%=wl%></span>
                            </td>
                            <td>
                                <%
                                String st = bean.getStatus();
                                String stBadge = "secondary";
                                if ("Critical".equals(st)) stBadge = "danger";
                                else if ("Alert".equals(st)) stBadge = "warning";
                                else if ("Active".equals(st)) stBadge = "success";
                                else if ("Inactive".equals(st)) stBadge = "secondary";
                                %>
                                <span class="badge bg-<%=stBadge%>"><%=st%></span>
                            </td>
                            <td><a href="WaterMonitoringCtl?id=<%=bean.getId()%>"
                                class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-pencil"></i> Edit
                            </a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>

            <div class="p-3 border-top">
                <%@ include file="ListFooter.jsp"%>
            </div>
        </form>
    </div>
</div>