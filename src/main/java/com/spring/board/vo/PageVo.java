package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 0;
	private String[] hiddenValue;
	private String searchInput;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String[] getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(String[] hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}
	
}
