package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.DoctorBean;
import com.sunilos.p4.model.DoctorModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/DoctorCtl")
public class DoctorCtl extends BaseCtl<DoctorBean, DoctorModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("doctorName"))) {
			request.setAttribute("doctorName", PropertyReader.getValue("error.require", "doctorName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("specialization"))) {
			request.setAttribute("specialization", PropertyReader.getValue("error.require", "specialization"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("experience"))) {
			request.setAttribute("experience", PropertyReader.getValue("error.require", "experience"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "contactNo"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected DoctorBean populateBean(HttpServletRequest request) {
		DoctorBean bean = new DoctorBean();
		bean.setDoctorName(DataUtility.getString(request.getParameter("doctorName")));
		bean.setSpecialization(DataUtility.getString(request.getParameter("specialization")));
		bean.setExperience(DataUtility.getString(request.getParameter("experience")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.DOCTOR_CTL;
		}
		return ORSView.DOCTOR_VIEW;
	}

	@Override
	protected DoctorModel getModel() {
		return new DoctorModel();
	}
}