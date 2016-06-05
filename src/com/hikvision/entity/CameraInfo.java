package com.hikvision.entity;

public class CameraInfo {
	private String deviceId;
	private String cameraId;
	private Integer cameraNo;
	private String cameraName;
	private Integer status;
	private Integer display;
	private Integer isShared;
	private String picUrl;
	private Integer isEncrypt;
	private Integer defence;
	private Integer videoLevel;
	
	
	private String deviceSerial;
	private String deviceName;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	public Integer getCameraNo() {
		return cameraNo;
	}
	public void setCameraNo(Integer cameraNo) {
		this.cameraNo = cameraNo;
	}
	public String getCameraName() {
		return cameraName;
	}
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public Integer getIsShared() {
		return isShared;
	}
	public void setIsShared(Integer isShared) {
		this.isShared = isShared;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getIsEncrypt() {
		return isEncrypt;
	}
	public void setIsEncrypt(Integer isEncrypt) {
		this.isEncrypt = isEncrypt;
	}
	public Integer getDefence() {
		return defence;
	}
	public void setDefence(Integer defence) {
		this.defence = defence;
	}
	public Integer getVideoLevel() {
		return videoLevel;
	}
	public void setVideoLevel(Integer videoLevel) {
		this.videoLevel = videoLevel;
	}
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
