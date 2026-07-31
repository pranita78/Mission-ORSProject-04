package com.sunilos.p4.ctl;

/**
 * Contains ORS View and Controller URI
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

public interface ORSView {

	public String APP_CONTEXT = "/ORSProject04";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";
	public String SUBSCRIPTION_VIEW = PAGE_FOLDER + "/SubscriptionView.jsp";
	public String SUBSCRIPTION_LIST_VIEW = PAGE_FOLDER + "/SubscriptionListView.jsp";
	public String FLIGHT_BOOKING_VIEW = PAGE_FOLDER + "/FlightBookingView.jsp";
	public String FLIGHT_BOOKING_LIST_VIEW = PAGE_FOLDER + "/FlightBookingListView.jsp";
	public String BUG_TRACKER_VIEW = PAGE_FOLDER + "/BugTrackerView.jsp";
	public String BUG_TRACKER_LIST_VIEW = PAGE_FOLDER + "/BugTrackerListView.jsp";
	public  String WATER_MONITORING_VIEW      = PAGE_FOLDER + "/WaterMonitoringView.jsp";
	public String WATER_MONITORING_LIST_VIEW = PAGE_FOLDER + "/WaterMonitoringListView.jsp";
	public String DRONE_DELIVERY_VIEW = PAGE_FOLDER + "/DroneDeliveryView.jsp";
	public String DRONE_DELIVERY_LIST_VIEW = PAGE_FOLDER + "/DroneDeliveryListView.jsp";
	public String SMART_LIGHT_VIEW = PAGE_FOLDER + "/SmartLightView.jsp";
	public String SMART_LIGHT_LIST_VIEW = PAGE_FOLDER + "/SmartLightListView.jsp";
	public String ENERGY_CONSUMPTION_VIEW = PAGE_FOLDER + "/EnergyConsumptionView.jsp";
	public String ENERGY_CONSUMPTION_LIST_VIEW = PAGE_FOLDER + "/EnergyConsumptionListView.jsp";
	public String VOICE_COMMAND_VIEW = PAGE_FOLDER + "/VoiceCommandView.jsp";
	public String VOICE_COMMAND_LIST_VIEW = PAGE_FOLDER + "/VoiceCommandListView.jsp";
	public String AI_RECOMMENDATION_VIEW = PAGE_FOLDER + "/AIRecommendationView.jsp";
	public String AI_RECOMMENDATION_LIST_VIEW = PAGE_FOLDER + "/AIRecommendationListView.jsp";
	public String WEATHER_ALERT_VIEW = PAGE_FOLDER + "/WeatherAlertView.jsp";
	public String WEATHER_ALERT_LIST_VIEW = PAGE_FOLDER + "/WeatherAlertListView.jsp";
	public String QR_SCANNER_VIEW = PAGE_FOLDER + "/QRScannerView.jsp";
	public String QR_SCANNER_LIST_VIEW = PAGE_FOLDER + "/QRScannerListView.jsp";
	public String EXAM_VIEW = PAGE_FOLDER + "/ExamView.jsp";
	public String EXAM_LIST_VIEW = PAGE_FOLDER + "/ExamListView.jsp";
	public String BRANCH_VIEW = PAGE_FOLDER + "/BranchView.jsp";
	public String BRANCH_LIST_VIEW = PAGE_FOLDER + "/BranchListView.jsp";


	public String ERROR_CTL = "/ctl/MarksheetCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";
	public String UPLOAD_PHOTO_CTL = APP_CONTEXT + "/ctl/uploadphoto";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";
	public String SUBSCRIPTION_CTL = APP_CONTEXT + "/ctl/SubscriptionCtl";
	public String SUBSCRIPTION_LIST_CTL = APP_CONTEXT + "/ctl/SubscriptionListCtl";
	public String FLIGHT_BOOKING_CTL = APP_CONTEXT + "/ctl/FlightBookingCtl";
	public String FLIGHT_BOOKING_LIST_CTL = APP_CONTEXT + "/ctl/FlightBookingListCtl";
	public String BUG_TRACKER_CTL = APP_CONTEXT + "/ctl/BugTrackerCtl";
	public String BUG_TRACKER_LIST_CTL = APP_CONTEXT + "/ctl/BugTrackerListCtl";
	public String WATER_MONITORING_CTL = APP_CONTEXT + "/ctl/WaterMonitoringCtl";
	public String WATER_MONITORING_LIST_CTL = APP_CONTEXT + "/ctl/WaterMonitoringListCtl";
	public String DRONE_DELIVERY_CTL = APP_CONTEXT + "/ctl/DroneDeliveryCtl";
	public String DRONE_DELIVERY_LIST_CTL = APP_CONTEXT + "/ctl/DroneDeliveryListCtl";
	public String SMART_LIGHT_CTL = APP_CONTEXT + "/ctl/SmartLightCtl";
	public String SMART_LIGHT_LIST_CTL = APP_CONTEXT + "/ctl/SmartLightListCtl";
	public String ENERGY_CONSUMPTION_CTL = APP_CONTEXT + "/ctl/EnergyConsumptionCtl";
	public String ENERGY_CONSUMPTION_LIST_CTL = APP_CONTEXT + "/ctl/EnergyConsumptionListCtl";
	public String VOICE_COMMAND_CTL = APP_CONTEXT + "/ctl/VoiceCommandCtl";
	public String VOICE_COMMAND_LIST_CTL = APP_CONTEXT + "/ctl/VoiceCommandListCtl";
	public String AI_RECOMMENDATION_CTL = APP_CONTEXT + "/ctl/AIRecommendationCtl";
	public String AI_RECOMMENDATION_LIST_CTL = APP_CONTEXT + "/ctl/AIRecommendationListCtl";
	public String WEATHER_ALERT_CTL = APP_CONTEXT + "/ctl/WeatherAlertCtl";
	public String WEATHER_ALERT_LIST_CTL = APP_CONTEXT + "/ctl/WeatherAlertListCtl";
	public String QR_SCANNER_CTL = APP_CONTEXT + "/ctl/QRScannerCtl";
	public String QR_SCANNER_LIST_CTL = APP_CONTEXT + "/ctl/QRScannerListCtl";
	public String EXAM_CTL = APP_CONTEXT + "/ctl/ExamCtl";
	public String EXAM_LIST_CTL = APP_CONTEXT + "/ctl/ExamListCtl";
	public String BRANCH_CTL = APP_CONTEXT + "/ctl/BranchCtl";
	public String BRANCH_LIST_CTL = APP_CONTEXT + "/ctl/BranchListCtl";

	
	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";

}