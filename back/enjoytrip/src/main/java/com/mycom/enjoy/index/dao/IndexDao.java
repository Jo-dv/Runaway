package com.mycom.enjoy.index.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.index.dto.InfoDto;

@Mapper
public interface IndexDao {
	InfoDto infoTotalCount();
}
