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
public class AttractionReplyDto {
	private int replyId;
	private int contentId;
	private int memberId;
	private String memberName;
	private String replyRegdt;
	private String replyContent;
	private boolean replyIsDeleted;
}
