package com.example.demo.web.api01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.web.api01.model.UserInfoDto;

@Mapper
public interface UserInfoDao {

	int insert(UserInfoDto dto);

	List<UserInfoDto> select(UserInfoDto dto);
}
