package com.example.demo.web.api01;

import java.io.Serializable;
import java.util.List;

import com.example.demo.web.api01.model.UserInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputParam implements Serializable {

	private static final long serialVersionUID = 583184444233475969L;
	private ResultCode resultCode;
	private List<UserInfoDto> users;

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
