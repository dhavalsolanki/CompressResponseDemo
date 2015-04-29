package com.gzip;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseBean {

	private String message;
	private String type;
	private String code;
	private Object data;
	private String notificationData;
	@JsonIgnore
	private Object tempData;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getNotificationData() {
		return notificationData;
	}

	public void setNotificationData(String notificationData) {
		this.notificationData = notificationData;
	}

	public Object getTempData() {
		return tempData;
	}

	public void setTempData(Object tempData) {
		this.tempData = tempData;
	}

	@Override
	public String toString() {
		return "ResponseBean [message=" + message + ", type=" + type
				+ ", code=" + code + ", data=" + data + ", notificationData="
				+ notificationData + ", tempData=" + tempData + "]";
	}

}
