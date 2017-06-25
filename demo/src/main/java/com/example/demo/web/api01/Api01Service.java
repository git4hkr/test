package com.example.demo.web.api01;

import static oldtricks.blogic.BLogicDataSourceConfig.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.api01.OutputParam.ResultCode;
import com.example.demo.web.api01.dao.UserInfoDao;
import com.example.demo.web.api01.model.UserInfoDto;
import com.example.demo.web.common.AbstractService;
import com.example.demo.web.common.CommonProperties;
import com.example.demo.web.common.model.SexType;

import lombok.extern.slf4j.Slf4j;
import oldtricks.blogic.BLogicDataSourceConfig;
import oldtricks.blogic.BLogicEntry;
import oldtricks.blogic.BLogicFunction;
import oldtricks.blogic.BLogicTransaction;
import oldtricks.util.DateTimeUtil2;

@Slf4j
@RestController
@EnableConfigurationProperties(Api01Properties.class)
public class Api01Service extends AbstractService {
	/** 外部設定値（共通） */
	@Autowired
	private CommonProperties commonProp;
	/** 外部設定値（API01用） */
	@Autowired
	private Api01Properties prop;
	/** ユーザー情報DAO */
	@Autowired
	private UserInfoDao userInfoDao;
	/** DATEフォーマッター */
	private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	/**
	 * 起動時の処理を記述します。
	 */
	@PostConstruct
	public void init() {
		log.info("AP version={}", commonProp.getVersion());
		log.info("startDatetime={}", timeFormat.format(prop.getStartDateTime()));
		log.info("endDatetime={}", timeFormat.format(prop.getStartDateTime().plusDays(10)));
		log.info("rundom uuid={}", prop.getUuid());
	}

	/**
	 * 終了時の処理を記述します。
	 */
	@PreDestroy
	public void destory() {
		log.info("bye..");
	}

	/**
	 * 業務ロジックのエントリー関数です。
	 *
	 * @param param
	 *            入力パラメータ
	 * @return 出力パラメータ
	 * @throws Exception
	 *             何らかの例外発生時
	 */
	@RequestMapping("/sample.do")
	@ResponseBody
	@BLogicEntry
	public OutputParam process(Param param) throws Exception {
		OutputParam ret = new OutputParam(ResultCode.NORMAL_END);
		updateShard(false);
		updateMaster(false);
		List<UserInfoDto> users = selectUserFromMaster(false);
		ret.setUsers(users);
		return ret;
	}

	/**
	 * 業務処理を記述します。
	 *
	 * @param throwEx
	 * @throws Exception
	 */
	@BLogicFunction(value = "updateMaster", showMsg = true, msgId = "MSG_0001")
	@BLogicDataSourceConfig(type = TYPE_MASTER, readReplica = false)
	@BLogicTransaction
	void updateMaster(boolean throwEx) throws Exception {
		insertUser("太郎", "山田", SexType.男性, throwEx);
	}

	/**
	 * 業務処理を記述します。
	 *
	 * @param throwEx
	 * @throws Exception
	 */
	@BLogicFunction(value = "selectUserFromMaster", showMsg = true, msgId = "MSG_0001")
	@BLogicDataSourceConfig(type = TYPE_MASTER, readReplica = false)
	@BLogicTransaction(readOnly = true)
	List<UserInfoDto> selectUserFromMaster(boolean throwEx) throws Exception {
		return selectMales();
	}

	/**
	 * 業務処理を記述します。
	 *
	 * @param throwEx
	 * @throws Exception
	 */
	@BLogicFunction(value = "updateShard", showMsg = true, msgId = "MSG_0002")
	@BLogicDataSourceConfig(type = TYPE_SHARD, readReplica = false)
	@BLogicTransaction
	void updateShard(boolean throwEx) {
		insertUser("花子", "山田", SexType.女性, throwEx);
	}

	/**
	 * ユーザー情報テーブルにINSERT発行。
	 *
	 * @param firstname
	 * @param lastname
	 * @param throwEx
	 * @return
	 */
	int insertUser(String firstname, String lastname, SexType sexType, boolean throwEx) {
		int ret = 0;
		UserInfoDto dto = UserInfoDto.builder().id("" + System.currentTimeMillis()).firstname(firstname)
				.lastname(lastname).sex(sexType).birthday(DateTimeUtil2.toTimestamp(prop.getStartDateTime()))
				.updatetime(DateTimeUtil2.toTimestamp(DateTimeUtil2.now())).build();
		ret = userInfoDao.insert(dto);
		if (throwEx)
			throw new IllegalStateException(lastname + firstname);
		return ret;
	}

	/**
	 * 男性のユーザー情報を取得します。
	 *
	 * @return
	 */
	public List<UserInfoDto> selectMales() {
		return userInfoDao.select(UserInfoDto.builder().sex(SexType.男性).build());
	}
}
