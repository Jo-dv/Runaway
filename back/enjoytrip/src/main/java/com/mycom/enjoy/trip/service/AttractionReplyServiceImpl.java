package com.mycom.enjoy.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.trip.dao.AttractionReplyDao;
import com.mycom.enjoy.trip.dto.AttractionReplyDto;
import com.mycom.enjoy.trip.dto.AttractionReplyParamDto;
import com.mycom.enjoy.trip.dto.AttractionReplyResultDto;

@Service
public class AttractionReplyServiceImpl  implements AttractionReplyService{
	private final AttractionReplyDao dao;
	private final int SUCCESS = 1;
	private final int FAIL = -1;

	public AttractionReplyServiceImpl(AttractionReplyDao dao) {
		this.dao = dao;
	}

	@Override
	public AttractionReplyResultDto getReply(AttractionReplyParamDto replyParamDto) {
		AttractionReplyResultDto resultDto = new AttractionReplyResultDto();
		try {
			int count = dao.attractionReplyTotalCount(replyParamDto.getContentId());
			resultDto.setCount(count);
			List<AttractionReplyDto> list = dao.getReply(replyParamDto);
			resultDto.setList(list);
			resultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public AttractionReplyResultDto replyInsert(AttractionReplyParamDto replyParamDto) {
		AttractionReplyResultDto resultDto = new AttractionReplyResultDto();
		System.out.println("insert는 들어온다.");
		try {
			dao.replyInsert(replyParamDto);
			resultDto.setResult(SUCCESS);
		}catch(Exception e) {
			System.out.println("Service Fail");
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public AttractionReplyResultDto replyUpdate(AttractionReplyParamDto replyParamDto) {
		AttractionReplyResultDto resultDto = new AttractionReplyResultDto();
		try {
			dao.replyUpdate(replyParamDto);
			resultDto.setResult(SUCCESS);
		}catch(Exception e) {
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public AttractionReplyResultDto replyDelete(int replyId) {
		AttractionReplyResultDto resultDto = new AttractionReplyResultDto();
		try {
			dao.replyDelete(replyId);
			resultDto.setResult(SUCCESS);
		}catch(Exception e) {
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}
	
}
