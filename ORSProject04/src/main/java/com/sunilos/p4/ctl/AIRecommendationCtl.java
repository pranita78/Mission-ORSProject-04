package com.sunilos.p4.ctl;

import java.util.LinkedHashMap;

import com.sunilos.p4.bean.AIRecommendationBean;
import com.sunilos.p4.model.AIRecommendationModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AIRecommendationCtl")
public class AIRecommendationCtl extends BaseCtl<AIRecommendationBean, AIRecommendationModel> {

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

		if (DataValidator.isNull(request.getParameter("recommendationCode"))) {
			request.setAttribute("recommendationCode", PropertyReader.getValue("error.require", "recommendationCode"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "userName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("recommendationType"))) {
			request.setAttribute("recommendationType", PropertyReader.getValue("error.require", "recommendationType"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected AIRecommendationBean populateBean(HttpServletRequest request) {
		AIRecommendationBean bean = new AIRecommendationBean();
		bean.setRecommendationCode(DataUtility.getString(request.getParameter("recommendationCode")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setRecommendationType(DataUtility.getString(request.getParameter("recommendationType")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.AI_RECOMMENDATION_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.AI_RECOMMENDATION_CTL;
		}
		return ORSView.AI_RECOMMENDATION_VIEW;
	}

	@Override
	protected AIRecommendationModel getModel() {
		return new AIRecommendationModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}