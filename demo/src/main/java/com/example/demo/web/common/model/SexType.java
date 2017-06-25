package com.example.demo.web.common.model;

import oldtricks.blogic.mybatis.typehandler.CodableEnum;

/**
 * テーブルにコード値で登録する場合は、以下の様に{@link CodableEnum}を実装したint型のENUMを作成します。<BR>
 * さらに、src/main/resources/mybatis/mybatis-config.xml にタイプハンドラーを登録します。 <BR>
<div> &lt;typeHandlers&gt; <br>
 * &lt;typeHandler handler=&quot;oldtricks.blogic.mybatis.typehandler.CodableEnumTypeHandler<E>&quot;
 * javaType=&quot;com.example.demo.web.common.model.SexType&quot; /&gt; <br>
 * &lt;/typeHandlers&gt; </div> *
 */
public enum SexType implements CodableEnum {
	男性(1), 女性(2), 不明(99);

	private final int code;

	SexType(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return code;
	}

	public int getCodeByName(String name) {
		return code;
	}

	@Override
	public CodableEnum fromCode(int code) throws IllegalArgumentException {
		for (CodableEnum c : SexType.values()) {
			if (c.getCode() == code)
				return c;
		}
		throw new IllegalArgumentException("" + code);
	}

}
