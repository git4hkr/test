package com.example.demo.web.api01;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.api01.OutputParam.ResultCode;
import com.example.demo.web.api01.dao.UserInfoDao;
import com.example.demo.web.api01.model.UserInfoDto;
import com.example.demo.web.common.AbstractService;

import oldtricks.blogic.BLogicDataSourceConfig;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicTransaction;

@RestController
public class Api01Service extends AbstractService {

	@Autowired
	private UserInfoDao userInfoDao;

	@BLogicTransaction
	@RequestMapping("/sample.do")
	@ResponseBody
	public OutputParam process(Param param) throws Exception {
		OutputParam ret = new OutputParam(ResultCode.NORMAL_END);
		updateMaster(false);
		updateShard(false);
		return ret;
	}

	@BLogicFunction(value = "updateMaster", showMsg = true, msgId = "MSG_0001")
	@BLogicDataSourceConfig(type = BLogicDataSourceConfig.TYPE_MASTER, readReplica = false)
	void updateMaster(boolean throwEx) throws Exception {
		insertUser("太郎", "山田", throwEx);
	}

	@BLogicFunction(value = "updateShard", showMsg = true, msgId = "MSG_0002")
	@BLogicDataSourceConfig(type = BLogicDataSourceConfig.TYPE_SHARD, readReplica = false)
	void updateShard(boolean throwEx) {
		insertUser("花子", "山田", throwEx);
	}

	int insertUser(String firstname, String lastname, boolean throwEx) {
		int ret = 0;
		UserInfoDto dto = UserInfoDto.builder().id("" + System.currentTimeMillis()).firstname(firstname)
				.lastname(lastname).updatetime(new Timestamp(System.currentTimeMillis())).build();
		ret = userInfoDao.insert(dto);
		if (throwEx)
			throw new IllegalStateException(lastname + firstname);
		return ret;
	}

}
