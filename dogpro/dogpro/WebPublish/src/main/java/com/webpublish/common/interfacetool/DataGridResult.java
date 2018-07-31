package com.webpublish.common.interfacetool;


public class DataGridResult extends ResultObject{
	public static final String STATE_SUCCESS = "success";
	public static final String STATE_FAIL = "fail";
	public static final Integer CODE_STATE_SUCCESS = 200;
	public static final Integer CODE_STATE_FAIL_DEFAULT = 101;//默认错误状态码
	public static final String Message_STATE_FAIL = "失败";
	public static final String Message_STATE_SUCCESS = "成功";
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * 提示
	 */
	private String message;
	
	/**
	 * 查询结果
	 */
	private Object rows;

	/**
	 * @return the 状态
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param 状态 the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the 状态码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param 状态码 the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the 提示
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param 提示 the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
	public static DataGridResult getSuccessResultObject() {
		DataGridResult o = new DataGridResult();
		o.setCode(CODE_STATE_SUCCESS);
		o.setState(STATE_SUCCESS);
		o.setMessage(Message_STATE_SUCCESS);
		return o;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
	
}