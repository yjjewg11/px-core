package com.company.news.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * cameraId		String	摄像头ID
cameraName		String	摄像头名称
channelNo		String	通道号
deviceSerial		String	设备序列号
isEncrypt		boolean	设备是否加密
isShared		boolean	是否是分享设备
picUrl		String	设备PC端设备的封面地址
isOnline		boolean	是否在线
 * @author liumingquan
 *
 */
@Entity
@Table(name = "ez_camera_info")
public class EZCameraInfo extends IdEntity {

	@Column
	private Timestamp create_time;// 创建时间
	@Column
	private String group_uuid;// 学校uuid.//-1,表示为配置.
	@Column
	private String class_uuid;// 班级uuid.0表示公共区域.-1,表示为配置.s
	
	@Column
	private String cameraId;// 坐标
	@Column
	private String cameraName;// 坐标
	@Column
	private String channelNo;// 电话
	@Column
	private String deviceSerial;// 
	@Column
	private Boolean isEncrypt;// 
	@Column
	private Boolean isShared;// 学生uuid
	@Column
	private String picUrl;// 学生名
	@Column
	private Boolean isOnline;// 坐标
	
	@Column
	private String deviceId;
	@Column
	private Integer cameraNo;
	@Column
	private Integer status;
	@Column
	private Integer display;
	@Column
	private Integer defence;
	@Column
	private Integer videoLevel;
	@Column
	private String deviceName;
	
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getCameraId() {
		return cameraId;
	}
	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public Boolean getIsEncrypt() {
		return isEncrypt;
	}
	public void setIsEncrypt(Boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}
	public Boolean getIsShared() {
		return isShared;
	}
	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getGroup_uuid() {
		return group_uuid;
	}
	public void setGroup_uuid(String group_uuid) {
		this.group_uuid = group_uuid;
	}
	public String getClass_uuid() {
		return class_uuid;
	}
	public void setClass_uuid(String class_uuid) {
		this.class_uuid = class_uuid;
	}
	public String getCameraName() {
		return cameraName;
	}
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getCameraNo() {
		return cameraNo;
	}
	public void setCameraNo(Integer cameraNo) {
		this.cameraNo = cameraNo;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


}
