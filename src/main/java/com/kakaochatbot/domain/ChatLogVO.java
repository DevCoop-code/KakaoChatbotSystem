package com.kakaochatbot.domain;

import java.util.Date;

public class ChatLogVO 
{
	private int bno;
	private String request;
	private String response;
	private String user;
	private Date regdate;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ChatLogVO [bno=" + bno + ", request=" + request + ", response=" + response + ", user=" + user
				+ ", regdate=" + regdate + "]";
	}
}
