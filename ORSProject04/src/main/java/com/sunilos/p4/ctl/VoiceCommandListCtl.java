package com.sunilos.p4.ctl;
import com.sunilos.p4.bean.VoiceCommandBean;
import com.sunilos.p4.model.VoiceCommandModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/VoiceCommandListCtl")
public class VoiceCommandListCtl extends BaseListCtl<VoiceCommandBean, VoiceCommandModel> {
	private static final long serialVersionUID = 1L;
	@Override
	protected VoiceCommandBean populateBean(HttpServletRequest request) {
		VoiceCommandBean bean = new VoiceCommandBean();
		bean.setCommandCode(DataUtility.getString(request.getParameter("commandCode")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setCommandText(DataUtility.getString(request.getParameter("commandText")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}
	@Override
	protected String getView() {
		return ORSView.VOICE_COMMAND_VIEW;
	}
	@Override
	protected String getView(String op) {
		return ORSView.VOICE_COMMAND_LIST_VIEW;
	}
	@Override
	protected VoiceCommandModel getModel() {
		return new VoiceCommandModel();
	}
}