package com.example.turing.models;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.turing.entity.Students;

public class Response {

    private String message;
    private List<Students> items;
    private HttpStatus code;
    private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Students> getItems() {
		return items;
	}
	public void setItems(List<Students> items) {
		this.items = items;
	}
	
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
    
    
}
