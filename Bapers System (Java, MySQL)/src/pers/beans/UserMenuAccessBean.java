package pers.beans;

public class UserMenuAccessBean {
	private boolean dashboard;
	private boolean staffManagement;
	private boolean customerManagement;
	private boolean bookings;
	private boolean reports;
	private boolean accountSettings;

	public UserMenuAccessBean(boolean dashboard, boolean staffManagement,
							  boolean customerManagement, boolean bookings,
							  boolean reports, boolean accountSettings) {
		super();
		this.dashboard = dashboard;
		this.staffManagement = staffManagement;
		this.customerManagement = customerManagement;
		this.bookings = bookings;
		this.reports = reports;
		this.accountSettings = accountSettings;
	}

	public boolean isDashboard() {
		return dashboard;
	}
	public void setDashboard(boolean dashboard) {
		this.dashboard = dashboard;
	}
	public boolean isStaffManagement() {
		return staffManagement;
	}
	public void setStaffManagement(boolean staffManagement) {
		this.staffManagement = staffManagement;
	}
	public boolean isCustomerManagement() {
		return customerManagement;
	}
	public void setCustomerManagement(boolean customerManagement) {
		this.customerManagement = customerManagement;
	}
	public boolean isBookings() {
		return bookings;
	}
	public void setBookings(boolean bookings) {
		this.bookings = bookings;
	}
	public boolean isReports() {
		return reports;
	}
	public void setReports(boolean reports) {
		this.reports = reports;
	}
	public boolean isAccountSettings() {
		return accountSettings;
	}
	public void setAccountSettings(boolean accountSettings) {
		this.accountSettings = accountSettings;
	}
}
