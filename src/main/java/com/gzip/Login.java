package com.gzip;

public class Login {

	private String userId;
	private String password;
	private String appVersion;
	private String macAddress;
	private String hostPcName;
	private String appType;
	private String hospitalType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getHostPcName() {
		return hostPcName;
	}

	public void setHostPcName(String hostPcName) {
		this.hostPcName = hostPcName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", password=" + password
				+ ", appVersion=" + appVersion + ", macAddress=" + macAddress
				+ ", hostPcName=" + hostPcName + ", appType=" + appType
				+ ", hospitalType=" + hospitalType + "]";
	}

}
