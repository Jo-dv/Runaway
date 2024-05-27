package com.mycom.enjoy.trip.service;

import com.mycom.enjoy.trip.dto.AttractionReplyParamDto;
import com.mycom.enjoy.trip.dto.AttractionReplyResultDto;

public interface AttractionReplyService {
	AttractionReplyResultDto getReply(AttractionReplyParamDto replyParamDto); 
	AttractionReplyResultDto replyInsert(AttractionReplyParamDto replyParamDto);
	AttractionReplyResultDto replyUpdate(AttractionReplyParamDto replyParamDto);
	AttractionReplyResultDto replyDelete(int replyId);
	// total count = > get에서 총괄해서 사용.
//	int attractionReplyTotalCount(int contentId);
}
