package com.hikvision.entity;

public class Page {
	private Long total;
	private Long page;
	private Long size;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
}
