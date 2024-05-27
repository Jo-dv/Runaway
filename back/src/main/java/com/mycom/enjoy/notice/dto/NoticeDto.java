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
public class NoticeDto {
	private int noticeId;
	private int memberId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeRegdt;
	private int noticeReadcount;
	private String memberName;
	private boolean sameUser;
}
