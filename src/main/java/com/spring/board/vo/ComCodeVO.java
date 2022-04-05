package com.spring.board.vo;

public class ComCodeVO {
	
	private String codeType;
	private String codeId;
	private String codeName;
	private String creator;
	private String create_time; 
	private String modeifier;
	private String modified_time;
	 
	 public ComCodeVO() {
		// TODO Auto-generated constructor stub
	}

	public ComCodeVO(String codeType, String codeId, String codeName, String creator, String create_time,
			String modeifier, String modified_time) {
		super();
		this.codeType = codeType;
		this.codeId = codeId;
		this.codeName = codeName;
		this.creator = creator;
		this.create_time = create_time;
		this.modeifier = modeifier;
		this.modified_time = modified_time;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModeifier() {
		return modeifier;
	}

	public void setModeifier(String modeifier) {
		this.modeifier = modeifier;
	}

	public String getModified_time() {
		return modified_time;
	}

	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	 
}
