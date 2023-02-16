package model.dto;

import java.sql.Date;

public class VisitDTO {
	/* mapper 의 database 컬럼명과 멤버변수명을 동일하게(대소문자 구분 안함.) 할 경우 
	   mybatis가 자동으로 database에서 맞는 컬럼명을 멤버변수명에 인식해서 매핑해줌.*/
	  
	private int id;
	private String userId;
	private String context;
	private Date createDate;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "VisitDTO [id=" + id + ", userId=" + userId + ", context=" + context + ", createDate=" + createDate
				+ "]";
	}



}
