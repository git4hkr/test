package com.example.demo.model;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Sun May 21 13:21:35 JST 2017
 */
import java.io.Serializable;

import oldtricks.bean.AbstractBean;

/**
 * View_売上データVo.
 *
 * @author devuser
 * @version 1.0 history Symbol Date Person Note [1] 2017/05/21 devuser
 *          Generated.
 */
public class 売上データ extends AbstractBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4472158602821915194L;

	public static final String TABLE = "VIEW_売上データ";

	/**
	 * id:text(2147483647)
	 */
	private String id;

	/**
	 * 担当部門名:text(2147483647)
	 */
	private String 担当部門名;

	/**
	 * 案件no:text(2147483647)
	 */
	private String 案件no;

	/**
	 * 工注no:text(2147483647)
	 */
	private String 工注no;

	/**
	 * 工注状況:text(2147483647)
	 */
	private String 工注状況;

	/**
	 * ユーザ名:text(2147483647)
	 */
	private String ユーザ名;

	/**
	 * システム名:text(2147483647)
	 */
	private String システム名;

	/**
	 * 処理勘定:text(2147483647)
	 */
	private String 処理勘定;

	/**
	 * 工注区分:text(2147483647)
	 */
	private String 工注区分;

	/**
	 * 売上年月:text(2147483647)
	 */
	private String 売上年月;

	/**
	 * 集計期間内工注売価:text(2147483647)
	 */
	private String 集計期間内工注売価;

	/**
	 * 集計期間内売上原価:text(2147483647)
	 */
	private String 集計期間内売上原価;

	/**
	 * 集計期間内粗利:text(2147483647)
	 */
	private String 集計期間内粗利;

	/**
	 * 集計期間内原価率:text(2147483647)
	 */
	private String 集計期間内原価率;

	/**
	 * 年度:text(2147483647)
	 */
	private String 年度;

	/**
	 * Constractor
	 */
	public 売上データ() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String get担当部門名() {
		return this.担当部門名;
	}

	public void set担当部門名(String 担当部門名) {
		this.担当部門名 = 担当部門名;
	}

	public String get案件no() {
		return this.案件no;
	}

	public void set案件no(String 案件no) {
		this.案件no = 案件no;
	}

	public String get工注no() {
		return this.工注no;
	}

	public void set工注no(String 工注no) {
		this.工注no = 工注no;
	}

	public String get工注状況() {
		return this.工注状況;
	}

	public void set工注状況(String 工注状況) {
		this.工注状況 = 工注状況;
	}

	public String getユーザ名() {
		return this.ユーザ名;
	}

	public void setユーザ名(String ユーザ名) {
		this.ユーザ名 = ユーザ名;
	}

	public String getシステム名() {
		return this.システム名;
	}

	public void setシステム名(String システム名) {
		this.システム名 = システム名;
	}

	public String get処理勘定() {
		return this.処理勘定;
	}

	public void set処理勘定(String 処理勘定) {
		this.処理勘定 = 処理勘定;
	}

	public String get工注区分() {
		return this.工注区分;
	}

	public void set工注区分(String 工注区分) {
		this.工注区分 = 工注区分;
	}

	public String get売上年月() {
		return this.売上年月;
	}

	public void set売上年月(String 売上年月) {
		this.売上年月 = 売上年月;
	}

	public String get集計期間内工注売価() {
		return this.集計期間内工注売価;
	}

	public void set集計期間内工注売価(String 集計期間内工注売価) {
		this.集計期間内工注売価 = 集計期間内工注売価;
	}

	public String get集計期間内売上原価() {
		return this.集計期間内売上原価;
	}

	public void set集計期間内売上原価(String 集計期間内売上原価) {
		this.集計期間内売上原価 = 集計期間内売上原価;
	}

	public String get集計期間内粗利() {
		return this.集計期間内粗利;
	}

	public void set集計期間内粗利(String 集計期間内粗利) {
		this.集計期間内粗利 = 集計期間内粗利;
	}

	public String get集計期間内原価率() {
		return this.集計期間内原価率;
	}

	public void set集計期間内原価率(String 集計期間内原価率) {
		this.集計期間内原価率 = 集計期間内原価率;
	}

	public String get年度() {
		return this.年度;
	}

	public void set年度(String 年度) {
		this.年度 = 年度;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[View_売上データVo:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" 担当部門名: ");
		buffer.append(担当部門名);
		buffer.append(" 案件no: ");
		buffer.append(案件no);
		buffer.append(" 工注no: ");
		buffer.append(工注no);
		buffer.append(" 工注状況: ");
		buffer.append(工注状況);
		buffer.append(" ユーザ名: ");
		buffer.append(ユーザ名);
		buffer.append(" システム名: ");
		buffer.append(システム名);
		buffer.append(" 処理勘定: ");
		buffer.append(処理勘定);
		buffer.append(" 工注区分: ");
		buffer.append(工注区分);
		buffer.append(" 売上年月: ");
		buffer.append(売上年月);
		buffer.append(" 集計期間内工注売価: ");
		buffer.append(集計期間内工注売価);
		buffer.append(" 集計期間内売上原価: ");
		buffer.append(集計期間内売上原価);
		buffer.append(" 集計期間内粗利: ");
		buffer.append(集計期間内粗利);
		buffer.append(" 集計期間内原価率: ");
		buffer.append(集計期間内原価率);
		buffer.append(" 年度: ");
		buffer.append(年度);
		buffer.append("]");
		return buffer.toString();
	}

}
