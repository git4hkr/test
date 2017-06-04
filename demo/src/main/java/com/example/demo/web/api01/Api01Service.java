package com.example.demo.web.api01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ShardNoResolverFilter;
import com.example.demo.web.api01.OutputParam.ResultCode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oldtricks.blogic.BLogic;
import oldtricks.blogic.BLogicDataSourceConfig;
import oldtricks.blogic.BLogicExceptionHandler;
import oldtricks.blogic.BLogicFilters;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicShardNoResolver;
import oldtricks.blogic.BLogicTransaction;
import oldtricks.blogic.SysLog;
import oldtricks.blogic.exception.BLogicException;

@Slf4j
@Data
@RestController
@BLogic
@BLogicFilters(value = { ShardNoResolverFilter.class })
public class Api01Service implements BLogicShardNoResolver {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BLogicTransaction
	@RequestMapping("/sample.do")
	@ResponseBody
	public OutputParam process(Param param) throws Exception {
		OutputParam ret = new OutputParam(ResultCode.NORMAL_END);
		updateMaster(false);
		updateShard(true);
		return ret;
	}

	@BLogicFunction(value = "updateMaster", showMsg = true, msgId = "MSG_0001")
	@BLogicDataSourceConfig(type = BLogicDataSourceConfig.TYPE_MASTER, readReplica = false)
	void updateMaster(boolean throwEx) {
		func1("master", throwEx);
	}

	@BLogicFunction(value = "updateShard", showMsg = true, msgId = "MSG_0002")
	@BLogicDataSourceConfig(type = BLogicDataSourceConfig.TYPE_SHARD, readReplica = false)
	void updateShard(boolean throwEx) {
		func1("shard", throwEx);
	}

	int func1(String dsName, boolean throwEx) {
		final int ret_ = jdbcTemplate.update("INSERT INTO 案件事業部map VALUES(?, ?)", System.currentTimeMillis(), dsName);
		if (throwEx)
			throw new IllegalStateException(dsName);
		return ret_;
	}

	@BLogicExceptionHandler
	public OutputParam handleException(BLogicException ex) {
		SysLog.syslog(ex);
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
