package com.example.demo.model;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Sun May 21 15:26:30 JST 2017
 */
import java.io.Serializable;

import oldtricks.bean.AbstractBean;

/**
 * 案件事業部mapVo.
 *
 * @author devuser
 * @version 1.0 history Symbol Date Person Note [1] 2017/05/21 devuser
 *          Generated.
 */
public class 案件事業部マップ extends AbstractBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2340057022612191232L;

	public static final String TABLE = "案件事業部MAP";

	/**
	 * id:text(2147483647)
	 */
	private String id;

	/**
	 * 事業部:text(2147483647)
	 */
	private String 事業部;

	/**
	 * Constractor
	 */
	public 案件事業部マップ() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get事業部() {
		return this.事業部;
	}

	public void set事業部(String 事業部) {
		this.事業部 = 事業部;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[案件事業部マップ:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" 事業部: ");
		buffer.append(事業部);
		buffer.append("]");
		return buffer.toString();
	}

}
