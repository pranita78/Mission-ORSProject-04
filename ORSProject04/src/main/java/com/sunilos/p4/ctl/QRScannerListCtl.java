package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.QRScannerBean;
import com.sunilos.p4.model.QRScannerModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/QRScannerListCtl")
public class QRScannerListCtl extends BaseListCtl<QRScannerBean, QRScannerModel> {

	private static final long serialVersionUID = 1L;

	@Override
	protected QRScannerBean populateBean(HttpServletRequest request) {
		QRScannerBean bean = new QRScannerBean();
		bean.setQrCode(DataUtility.getString(request.getParameter("qrCode")));
		bean.setScannedBy(DataUtility.getString(request.getParameter("scannedBy")));
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
		return ORSView.QR_SCANNER_LIST_VIEW;
	}

	@Override
	protected QRScannerModel getModel() {
		return new QRScannerModel();
	}
}