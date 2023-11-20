package com.mycom.enjoy.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoy.notice.dto.NoticeDto;
import com.mycom.enjoy.notice.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {
	int noticeInsert(NoticeDto dto);
	int noticeUpdate(NoticeDto dto);
	int noticeDelete(int noticeId);
	NoticeDto noticeDetail(int noticeId);
	List<NoticeDto> noticeList(NoticeParamDto noticeParamDto);
	//구현 필요 ( 11/17 ) 
	int noticeListTotalCount();
	//noticeUserRead
	//map-@param
	int noticeUserReadInsert(@Param("noticeId") int noticeId, @Param("memberId") int memberId);
	int noticeReadCountUpdate(int noticeId);
	int noticeUserReadCount(NoticeParamDto noticeParamDto);
	int noticeReadCountDelete(int noticeId);
	
	//검색
	List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto); //검색어
	int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto); //검색어 가진 총 건수
}
