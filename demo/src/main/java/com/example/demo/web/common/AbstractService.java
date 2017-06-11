package com.example.demo.web.common;

import com.example.demo.ShardNoResolverFilter;
import com.example.demo.web.api01.OutputParam;
import com.example.demo.web.api01.OutputParam.ResultCode;

import lombok.extern.slf4j.Slf4j;
import oldtricks.blogic.BLogic;
import oldtricks.blogic.BLogicExceptionHandler;
import oldtricks.blogic.BLogicFilters;
import oldtricks.blogic.BLogicShardNoResolver;
import oldtricks.blogic.SysLog;
import oldtricks.blogic.exception.BLogicException;

@Slf4j
@BLogic
@BLogicFilters(value = { ShardNoResolverFilter.class })
public class AbstractService implements BLogicShardNoResolver {

	@BLogicExceptionHandler
	public OutputParam handleException(BLogicException ex) {
		SysLog.syslog(ex);
		log.error("", ex);
		return new OutputParam(ResultCode.SYSTEM_ERR);
	}

	@BLogicExceptionHandler
	public OutputParam handleException(Throwable ex) {
		SysLog.syslog("MSG_9999", "致命的なエラーが発生しました。");
		log.error("", ex);
		return new OutputParam(ResultCode.SYSTEM_ERR);
	}

	@Override
	public int resolveShardNo() throws Exception {
		return ShardNoResolverFilter.getShardNo();
	}

}
