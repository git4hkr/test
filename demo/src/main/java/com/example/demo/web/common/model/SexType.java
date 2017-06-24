package com.example.demo.web.common.model;

import com.example.demo.mybatis.CodableEnum;

public enum SexType implements CodableEnum {
	男性(0), 女性(1), 不明(3);

	private final int code;

	SexType(int code) {
		this.code = code;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see sample.CodableEnum#getCode()
	 */
	@Override
	public int getCode() {
		return code;
	}

	public int getCodeByName(String name) {
		return code;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see sample.CodableEnum#fromCode(java.lang.String)
	 */
	@Override
	public CodableEnum fromCode(int code) throws IllegalArgumentException {
		for (CodableEnum c : SexType.values()) {
			if (c.getCode() == code)
				return c;
		}
		throw new IllegalArgumentException("" + code);
	}

}
