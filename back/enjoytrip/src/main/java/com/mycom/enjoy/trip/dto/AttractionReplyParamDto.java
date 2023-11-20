package com.mycom.enjoy.trip.dto;

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
public class AttractionReplyParamDto {
	private int contentId;
	private int memberId;
	private int replyId;
	private String replyContent;
	private int limit;
}
