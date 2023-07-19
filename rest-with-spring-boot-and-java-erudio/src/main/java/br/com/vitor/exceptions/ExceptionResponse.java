package br.com.vitor.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;
	private String datails;
	
	public ExceptionResponse(Date timestamp, String message, String datails) {
		this.timestamp = timestamp;
		this.message = message;
		this.datails = datails;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDatails() {
		return datails;
	}
	public void setDatails(String datails) {
		this.datails = datails;
	}
	

}
