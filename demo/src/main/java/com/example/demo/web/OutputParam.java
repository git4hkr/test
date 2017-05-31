package com.example.demo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.売上データ;

import lombok.Data;

@Data
public class OutputParam implements Serializable {

	private static final long serialVersionUID = 583184444233475969L;
	private String name = "AAAAAA";
	private List<売上データ> 売上データs = new ArrayList<>();
	private ResultCode resultCode;

	public OutputParam(ResultCode resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public enum ResultCode {
		NORMAL_END("0000"), /* 正常 */
		PARAM_ERR("0101"), /* パラメータエラー */
		LOGIC_ERR("0201"), /* 業務エラー */
		SYSTEM_ERR("0301") /* システムエラー */;

		private final String val;

		private ResultCode(String val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return val;
		}
	}

}
