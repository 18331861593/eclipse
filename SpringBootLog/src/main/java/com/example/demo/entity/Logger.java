package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logger")
public class Logger implements Serializable{
	private static final long serialVersionUID = 5812928807242852387L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	//请求的客户端ip
	@Column(name="client_ip")
	private String client_ip;
	
	//请求地址
	@Column(name="url")
	private String url;
	
	@Column(name="type")
	private String type;
	
	//请求方法 get or post
	@Column(name="method")
	private String method;
	
	//请求参数
	@Column(name="param_data")
	private String param_data;
	
	///sessionid
	@Column(name="session_id")
	private String session_id;
	
	@Column(name="time")
	private Timestamp time;
	
	@Column(name="return_time")
	private String return_time;
	
	@Column(name="return_data")
	private String return_data;
	
	public String getReturn_data() {
		return return_data;
	}

	public void setReturn_data(String return_data) {
		this.return_data = return_data;
	}

	//http 状态码
	@Column(name="http_status_code")
	private String http_status_code;
	
	//时间差
	@Column(name="time_consuming")
	private String time_consuming;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam_data() {
		return param_data;
	}

	public void setParam_data(String param_data) {
		this.param_data = param_data;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getReturn_time() {
		return return_time;
	}

	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}

	public String getHttp_status_code() {
		return http_status_code;
	}

	public void setHttp_status_code(String http_status_code) {
		this.http_status_code = http_status_code;
	}

	public String getTime_consuming() {
		return time_consuming;
	}

	public void setTime_consuming(String time_consuming) {
		this.time_consuming = time_consuming;
	}

	
}
