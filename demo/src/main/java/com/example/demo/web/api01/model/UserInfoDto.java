package com.example.demo.web.api01.model;

import com.example.demo.web.common.model.SexType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

	public static final String TABLE = "T_USERS";
	/**
	 * ユーザー識別子:varchar(32) <Primary Key>
	 */
	private String id;

	/**
	 * 氏:varchar(32)
	 */
	private String lastname;

	/**
	 * 名:varchar(32)
	 */
	private String firstname;

	/**
	 * 性別 : 0：男性 1：女性 3：不明:int(10)
	 */
	private SexType sex;

	/**
	 * 生年月日:datetime(0)
	 */
	private java.sql.Timestamp birthday;

	/**
	 * 更新日時:timestamp(0)
	 */
	private java.sql.Timestamp updatetime;

}
