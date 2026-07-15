package com.sunilos.p4.ctl;
import java.util.LinkedHashMap;
import com.sunilos.p4.bean.VoiceCommandBean;
import com.sunilos.p4.model.VoiceCommandModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/VoiceCommandCtl")
public class VoiceCommandCtl extends BaseCtl<VoiceCommandBean, VoiceCommandModel> {
	private static final long serialVersionUID = 1L;
	//private void preload(HttpServletRequest request) {
		//LinkedHashMap<String, String> statusList = new LinkedHashMap<String, String>();
		//statusList.put("ON", "ON");
		//statusList.put("OFF", "OFF");
		//request.setAttribute("statusList", statusList);
	//}
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("commandCode"))) {
			request.setAttribute("commandCode", PropertyReader.getValue("error.require", "commandCode"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "userName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("commandText"))) {
			request.setAttribute("commandText", PropertyReader.getValue("error.require", "commandText"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}
		return pass;
	}
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
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.VOICE_COMMAND_CTL;
		}
		return ORSView.VOICE_COMMAND_VIEW;
	}
	@Override
	protected VoiceCommandModel getModel() {
		return new VoiceCommandModel();
	}
	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}