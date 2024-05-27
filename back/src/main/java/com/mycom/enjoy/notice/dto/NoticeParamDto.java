package com.mycom.enjoy.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeParamDto {
	private int limit;
	private int offset;
	private int noticeId;
	private int memberId;
	private String searchWord;
}
