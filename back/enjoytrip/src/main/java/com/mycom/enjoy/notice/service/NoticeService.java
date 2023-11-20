package com.mycom.enjoy.notice.service;

import com.mycom.enjoy.notice.dto.NoticeDto;
import com.mycom.enjoy.notice.dto.NoticeParamDto;
import com.mycom.enjoy.notice.dto.NoticeResultDto;

public interface NoticeService {
	NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto);

	NoticeResultDto noticeDelete(int noticeId);

	NoticeResultDto noticeUpdate(NoticeDto dto);

	NoticeResultDto noticeInsert(NoticeDto dto);

	NoticeResultDto noticeList(NoticeParamDto noticeParamDto);

	// int noticeListTotalCount();
	NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto);
}
