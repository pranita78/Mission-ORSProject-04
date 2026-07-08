package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.FlightBookingBean;
import com.sunilos.p4.model.FlightBookingModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FlightBookingCtl")
public class FlightBookingCtl extends BaseCtl<FlightBookingBean, FlightBookingModel> {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean validate(HttpServletRequest request) {
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("passengerName"))) {
            request.setAttribute("passengerName", PropertyReader.getValue("error.require", "passengerName"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("flightNo"))) {
            request.setAttribute("flightNo", PropertyReader.getValue("error.require", "flightNo"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("source"))) {
            request.setAttribute("source", PropertyReader.getValue("error.require", "source"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("destination"))) {
            request.setAttribute("destination", PropertyReader.getValue("error.require", "destination"));
            pass = false;
        }

        return pass;
    }

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
        if (OP_CANCEL.equalsIgnoreCase(op)) {
            return ORSView.FLIGHT_BOOKING_CTL;
        }
        return ORSView.FLIGHT_BOOKING_VIEW;
    }

    @Override
    protected FlightBookingModel getModel() {
        return new FlightBookingModel();
    }
}