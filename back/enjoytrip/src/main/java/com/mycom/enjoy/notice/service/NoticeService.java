package com.mycom.enjoy.notice.service;

import java.util.List;

import com.mycom.enjoy.notice.dto.NoticeDto;

public interface NoticeService {
	List<NoticeDto> noticeList();
	int noticeRegister(NoticeDto dto);
	int noticeUpdate(NoticeDto dto);
	int noticeDelete(int noticeId);
	NoticeDto noticeDetail(int noticeId);
}
