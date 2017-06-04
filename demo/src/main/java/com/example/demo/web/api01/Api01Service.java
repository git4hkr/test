package com.example.demo.web.api01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.api01.OutputParam.ResultCode;
import com.example.demo.web.common.AbstractService;

import oldtricks.blogic.BLogicDataSourceConfig;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicTransaction;

@RestController
public class Api01Service extends AbstractService {

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
	void updateMaster(boolean throwEx) throws Exception {
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

}
