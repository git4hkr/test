package com.example.demo.web.api01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.api01.OutputParam.ResultCode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oldtricks.blogic.BLogic;
import oldtricks.blogic.BLogicDataSourceConfig;
import oldtricks.blogic.BLogicExceptionHandler;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicShardNoResolver;
import oldtricks.blogic.BLogicTransaction;
import oldtricks.blogic.SysLog;
import oldtricks.blogic.exception.BLogicException;

@Slf4j
@Data
@RestController
public class Api01Service implements BLogic<Param, OutputParam> ,BLogicShardNoResolver {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@BLogicTransaction
	@RequestMapping("/sample.do")
	@ResponseBody
	public OutputParam process(Param param) throws Exception {
		OutputParam ret = new OutputParam(ResultCode.NORMAL_END);
		func1("pgXADataSource1", false);
		func1("pgXADataSource2", false);
		func1("pgXADataSource1", false);
		return ret;
	}

	@BLogicFunction(value = "func1", showMsg = true, msgId = "MSG_0001")
	@BLogicDataSourceConfig(type = BLogicDataSourceConfig.TYPE_MASTER, readReplica = true)
	int func1(String dsName, boolean throwEx) {
		final int ret_ = jdbcTemplate.update("INSERT INTO 案件事業部map VALUES(?, ?)", System.currentTimeMillis(), dsName);
		if (throwEx)
			throw new IllegalStateException(dsName);
		return ret_;
	}

	@BLogicExceptionHandler
	public OutputParam handleException(BLogicException ex) {
		SysLog.syslog(ex);
		log.error("", ex);
		return new OutputParam(ResultCode.SYSTEM_ERR);
	}

	@BLogicExceptionHandler
	public OutputParam handleException(Throwable ex) {
		SysLog.syslog("MSG_9999", "致命的なエラーが発生しました。");
		return new OutputParam(ResultCode.SYSTEM_ERR);
	}

	@Override
	public int resolveShrdNo(Object[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
}
