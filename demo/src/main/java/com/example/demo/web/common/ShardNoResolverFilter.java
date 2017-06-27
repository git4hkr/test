package com.example.demo.web.common;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.web.api01.Param;

import oldtricks.blogic.BLogicFilter;

/**
 * シャード番号をスレッドローカルに保持するフィルターです。
 *
 */
public class ShardNoResolverFilter implements BLogicFilter {

	/** シャード番号保持用スレッドローカル */
	private static final ThreadLocal<Integer> SHARD_NUM = new ThreadLocal<>();

	/**
	 * シャード番号を取得します。
	 *
	 * @return シャード番号
	 */
	public static int getShardNo() {
		return SHARD_NUM.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(Method method) {
		return method.getAnnotation(RequestMapping.class) == null ? false : true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object intercept(Object target, Method method, Object[] args, Next next) throws Throwable {
		try {
			SHARD_NUM.set(((Param) args[0]).getShardNo());
			return next.invoke(target, method, args);
		} finally {
			SHARD_NUM.remove();
		}
	}

}
