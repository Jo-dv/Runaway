package com.mycom.enjoy.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.notice.dao.NoticeDao;
import com.mycom.enjoy.notice.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService{
	private final NoticeDao dao;
	public NoticeServiceImpl(NoticeDao dao) {
		this.dao = dao;
	}

	@Override
	public List<NoticeDto> noticeList() {
		return dao.noticeList();
	}

	@Override
	public int noticeRegister(NoticeDto dto) {
		return dao.noticeRegister(dto);
	}

	@Override
	public int noticeUpdate(NoticeDto dto) {
		return dao.noticeUpdate(dto);
	}

	@Override
	public int noticeDelete(int noticeId) {
		return dao.noticeDelete(noticeId);
	}

	@Override
	public NoticeDto noticeDetail(int noticeId) {
		return dao.noticeDetail(noticeId);
	}

}
