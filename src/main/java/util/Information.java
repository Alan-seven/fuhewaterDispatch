/*
 * @(#) Algorithm.java           1.0 2015/1/15/ ChaoWang
 *
 * Copyright(C) 2011-2015 ChaoWang   All Rights Reserved
 */
package util;

import java.io.Serializable;

public class Information implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 7482539776116569949L;

	/**返回信息*/
	private String message;
	/**详细信息*/
	private String messageDetail;
	/**是否成功*/
	private boolean successful = false;
	/**返回单值*/
	private Object result;
	/**返回序列值*/
	private Object[] results;
	
	/**增加结果信息*/
	public void addMessage(String message){
		this.message = this.message +message;
	}
	
	/**增加详细信息*/
	public void addMessageDetail(String message){
		this.messageDetail = this.messageDetail +message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Object[] getResults() {
		return results;
	}
	public void setResults(Object[] results) {
		this.results = results;
	}
	public String getMessageDetail() {
		return messageDetail;
	}
	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}
	
	
}
