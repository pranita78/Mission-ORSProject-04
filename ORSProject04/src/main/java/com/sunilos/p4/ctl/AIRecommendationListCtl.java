package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.AIRecommendationBean;
import com.sunilos.p4.model.AIRecommendationModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AIRecommendationListCtl")
public class AIRecommendationListCtl extends BaseListCtl<AIRecommendationBean, AIRecommendationModel> {

	private static final long serialVersionUID = 1L;

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
		return ORSView.AI_RECOMMENDATION_LIST_VIEW;
	}

	@Override
	protected AIRecommendationModel getModel() {
		return new AIRecommendationModel();
	}
}