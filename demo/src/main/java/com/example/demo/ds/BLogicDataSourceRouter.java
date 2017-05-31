package com.example.demo.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class BLogicDataSourceRouter extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> SHARD_UNIQUE_RESOURCE_NAME = new ThreadLocal<>();

	public static String getUniqueResourceName() {
		return SHARD_UNIQUE_RESOURCE_NAME.get();
	}

	public static void setUniqueRsourceName(String uniqueResourceName) {
		SHARD_UNIQUE_RESOURCE_NAME.set(uniqueResourceName);
	}

	public static void clearUniqueResourceName() {
		SHARD_UNIQUE_RESOURCE_NAME.remove();
	}

	public static boolean isAbsent() {
		return getUniqueResourceName() == null;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getUniqueResourceName();
	}

}
