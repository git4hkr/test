package com.example.demo.web.api01.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

	public static final String TABLE = "T_USERS";

	/** ユーザー識別子:varchar(32) <Primary Key */
	private String id;

	/** 氏:varchar(32) */
	private String lastname;

	/** 名:varchar(32) */
	private String firstname;

	/** 更新日時:timestamp(0) */
	private java.sql.Timestamp updatetime;

}
