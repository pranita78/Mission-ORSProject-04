package com.sunilos.p4.ctl;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

import com.sunilos.p4.bean.QRScannerBean;
import com.sunilos.p4.model.QRScannerModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/QRScannerCtl")
public class QRScannerCtl extends BaseCtl<QRScannerBean, QRScannerModel> {

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

		if (DataValidator.isNull(request.getParameter("qrCode"))) {
			request.setAttribute("qrCode", PropertyReader.getValue("error.require", "qrCode"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("scannedBy"))) {
			request.setAttribute("scannedBy", PropertyReader.getValue("error.require", "scannedBy"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("scannedTime"))) {
			request.setAttribute("scannedTime", PropertyReader.getValue("error.require", "scannedTime"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected QRScannerBean populateBean(HttpServletRequest request) {
		QRScannerBean bean = new QRScannerBean();
		bean.setQrCode(DataUtility.getString(request.getParameter("qrCode")));
		bean.setScannedBy(DataUtility.getString(request.getParameter("scannedBy")));

		String scannedTimeStr = request.getParameter("scannedTime");
		if (scannedTimeStr != null && !scannedTimeStr.trim().isEmpty()) {
			try {
				SimpleDateFormat sdf;
				if (scannedTimeStr.length() > 16) {
					sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				} else {
					sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				}
				java.util.Date parsedDate = sdf.parse(scannedTimeStr);
				bean.setScannedTime(new java.sql.Timestamp(parsedDate.getTime()));
			} catch (Exception e) {
				System.out.println("Date parse error for value: " + scannedTimeStr);
				e.printStackTrace();
			}
		}

		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.QR_SCANNER_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			return ORSView.QR_SCANNER_CTL;
		}
		return ORSView.QR_SCANNER_VIEW;
	}

	@Override
	protected QRScannerModel getModel() {
		return new QRScannerModel();
	}

	protected void preShow(HttpServletRequest request) {
		preload(request);
	}
}