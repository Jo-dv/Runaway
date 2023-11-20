package com.mycom.enjoy.notice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeResultDto {
	private int result;
	private NoticeDto dto;//상세 1건
	private List<NoticeDto> list;//목록 여러건
	private int count;//총건수
}
