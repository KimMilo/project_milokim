package model.dto;

import java.sql.Date;

public class CommentDTO {
	
	private int id;
	private int bnum;
	private String writer;
	private String context;
	private Date createDate;
	private int cLevel;
	private int cRef;
	private int cReply_SEQ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public int getcLevel() {
		return cLevel;
	}
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
	public int getcRef() {
		return cRef;
	}
	public void setcRef(int cRef) {
		this.cRef = cRef;
	}
	public int getcReply_SEQ() {
		return cReply_SEQ;
	}
	public void setcReply_SEQ(int cReply_SEQ) {
		this.cReply_SEQ = cReply_SEQ;
	}
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", bnum=" + bnum + ", writer=" + writer + ", context=" + context
				+ ", createDate=" + createDate + ", cLevel=" + cLevel + ", cRef=" + cRef + ", cReply_SEQ=" + cReply_SEQ
				+ "]";
	}
	
	
	
}
