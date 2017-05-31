package com.example.demo.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ds.BLogicDataSourceRouter;
import com.example.demo.web.OutputParam.ResultCode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oldtricks.blogic.BLogic;
import oldtricks.blogic.BLogicExceptionHandler;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicTransaction;
import oldtricks.blogic.SysLog;
import oldtricks.blogic.exception.BLogicException;

@Slf4j
@Data
@RestController
public class SampleService implements BLogic<Param, OutputParam> {

	@Autowired
	private DataSource ds;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	@BLogicTransaction
	@RequestMapping("/sample.do")
	@ResponseBody
	public OutputParam process(Param param) throws Exception {
		OutputParam ret = new OutputParam(ResultCode.NORMAL_END);
		func1("pgXADataSource1", false);
		func1("pgXADataSource2", false);
		return ret;
	}

	@BLogicTransaction
	@BLogicFunction(value = "func1", showMsg = true, msgId = "MSG_0001")
	int func1(String dsName, boolean throwEx) {
		BLogicDataSourceRouter.setUniqueRsourceName(dsName);
		final int ret_ = jdbcTemplate.update("INSERT INTO 案件事業部map VALUES(?, ?)", System.currentTimeMillis(), dsName);
		BLogicDataSourceRouter.clearUniqueResourceName();

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
}
