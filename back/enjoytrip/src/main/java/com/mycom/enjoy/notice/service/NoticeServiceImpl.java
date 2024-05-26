package com.mycom.enjoy.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardResultDto;
import com.mycom.enjoy.notice.dao.NoticeDao;
import com.mycom.enjoy.notice.dto.NoticeDto;
import com.mycom.enjoy.notice.dto.NoticeParamDto;
import com.mycom.enjoy.notice.dto.NoticeResultDto;

@Service
public class NoticeServiceImpl implements NoticeService{
	private final NoticeDao dao;
	public NoticeServiceImpl(NoticeDao dao) {
		this.dao = dao;
	}

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	@Override
	@Transactional
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			int userReadCnt = dao.noticeUserReadCount(noticeParamDto);
			if (userReadCnt == 0) {
				dao.noticeUserReadInsert(noticeParamDto.getNoticeId(),noticeParamDto.getMemberId());
				dao.noticeReadCountUpdate(noticeParamDto.getNoticeId());
			}

			NoticeDto dto = dao.noticeDetail(noticeParamDto.getNoticeId());
			noticeResultDto.setDto(dto);
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}
	@Override
	@Transactional
	public NoticeResultDto noticeDelete(int noticeId) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			dao.noticeReadCountDelete(noticeId);
			dao.noticeDelete(noticeId);
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}
	@Override
	@Transactional
	public NoticeResultDto noticeUpdate(NoticeDto dto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			dao.noticeUpdate(dto);
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}
	@Override
	@Transactional
	public NoticeResultDto noticeInsert(NoticeDto dto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			dao.noticeInsert(dto);
			noticeResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		return noticeResultDto;
	}
	@Override
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			List<NoticeDto> list = dao.noticeList(noticeParamDto);
			int count = dao.noticeListTotalCount();
			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			noticeResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}

		return noticeResultDto;
	}
	@Override
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		try {
			List<NoticeDto> list = dao.noticeListSearchWord(noticeParamDto);
			int count = dao.noticeListSearchWordTotalCount(noticeParamDto);

			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			noticeResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}

		return noticeResultDto;
	}
	
	

}
