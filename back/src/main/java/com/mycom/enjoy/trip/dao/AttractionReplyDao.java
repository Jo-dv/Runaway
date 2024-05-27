package com.mycom.enjoy.trip.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.trip.dto.AttractionReplyDto;
import com.mycom.enjoy.trip.dto.AttractionReplyParamDto;

@Mapper
public interface AttractionReplyDao {
	List<AttractionReplyDto> getReply(AttractionReplyParamDto replyParamDto); 
	int replyInsert(AttractionReplyParamDto replyParamDto);
	int replyUpdate(AttractionReplyParamDto replyParamDto);
	int replyDelete(int contentId);
	int attractionReplyTotalCount(int contentId);
}
