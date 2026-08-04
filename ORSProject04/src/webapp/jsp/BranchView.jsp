<%@page import="com.sunilos.p4.ctl.BranchCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="com.sunilos.p4.bean.BranchBean"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.BranchBean"
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
				<i class="bi bi-building me-2"></i>
				<%=bean.getId() > 0 ? "Edit Branch" : "Add Branch"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success py-2">
				<i class="bi bi-check-circle-fill me-2"></i><%=_suc%></div>
			<%
			}
			%>
			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
			<%
			}
			%>

			<form action="<%=ORSView.BRANCH_CTL%>" method="POST">
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="mb-3">
					<label class="form-label fw-semibold">Branch Name <span
						class="text-danger">*</span></label> <input type="text" name="branchName"
						class="form-control" maxlength="100"
						value="<%=DataUtility.getStringData(bean.getBranchName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("branchName", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">City <span
						class="text-danger">*</span></label> <input type="text"
						name="city" class="form-control" maxlength="100"
						value="<%=DataUtility.getStringData(bean.getCity())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("city", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Manager Name <span
						class="text-danger">*</span></label> <input type="text"
						name="managerName" class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getManagerName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("managerName", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Contact No <span
						class="text-danger">*</span></label> <input type="text"
						name="contactNo" class="form-control" maxlength="15"
						value="<%=DataUtility.getStringData(bean.getContactNo())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("contactNo", request)%></div>
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