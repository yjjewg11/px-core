package com.hikvision.entity;

import java.util.List;

public class CameraInfoResult extends BaseResult {
	
	private Page page;
	private List<CameraInfo> data;

	public List<CameraInfo> getData() {
		return data;
	}

	public void setData(List<CameraInfo> data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
