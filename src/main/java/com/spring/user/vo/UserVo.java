package com.spring.user.vo;

public class UserVo {
	
	private String 	userId;
	private String 	userPw;
	private String 	userName;
	private String 	userPhone1;
	private String 	userPhone2;
	private String 	userPhone3;
	private String 	userAddr1;
	private String 	userAddr2;
	private String	userCompany;
	private String  creator;
	private String  createTime;
	private String  modifier;
	private String  modifiedTime;
	
	public UserVo() {
		// TODO Auto-generated constructor stub
	}
	
	public UserVo(String userId, String userPw, String userName, String userPhone1, String userPhone2,
			String userPhone3, String userAddr1, String userAddr2, String userCompany, String creator,
			String createTime, String modifier, String modifiedTime) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhone1 = userPhone1;
		this.userPhone2 = userPhone2;
		this.userPhone3 = userPhone3;
		this.userAddr1 = userAddr1;
		this.userAddr2 = userAddr2;
		this.userCompany = userCompany;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifiedTime = modifiedTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone1() {
		return userPhone1;
	}

	public void setUserPhone1(String userPhone1) {
		this.userPhone1 = userPhone1;
	}

	public String getUserPhone2() {
		return userPhone2;
	}

	public void setUserPhone2(String userPhone2) {
		this.userPhone2 = userPhone2;
	}

	public String getUserPhone3() {
		return userPhone3;
	}

	public void setUserPhone3(String userPhone3) {
		this.userPhone3 = userPhone3;
	}

	public String getUserAddr1() {
		return userAddr1;
	}

	public void setUserAddr1(String userAddr1) {
		this.userAddr1 = userAddr1;
	}

	public String getUserAddr2() {
		return userAddr2;
	}

	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
}
