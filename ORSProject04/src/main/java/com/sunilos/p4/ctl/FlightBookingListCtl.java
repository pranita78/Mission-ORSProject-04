package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.FlightBookingBean;
import com.sunilos.p4.model.FlightBookingModel;
import com.sunilos.p4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FlightBookingListCtl")
public class FlightBookingListCtl extends BaseListCtl<FlightBookingBean, FlightBookingModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected FlightBookingBean populateBean(HttpServletRequest request) {
        FlightBookingBean bean = new FlightBookingBean();
        bean.setPassengerName(DataUtility.getString(request.getParameter("passengerName")));
        bean.setFlightNo(DataUtility.getString(request.getParameter("flightNo")));
        bean.setSource(DataUtility.getString(request.getParameter("source")));
        bean.setDestination(DataUtility.getString(request.getParameter("destination")));
        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.FLIGHT_BOOKING_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.FLIGHT_BOOKING_LIST_VIEW;
    }

    @Override
    protected FlightBookingModel getModel() {
        return new FlightBookingModel();
    }
}