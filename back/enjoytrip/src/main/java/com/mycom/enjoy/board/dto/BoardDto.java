package com.mycom.enjoy.board.dto;

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
public class BoardDto {
	private int boardId;
	private int memberId;
	private String boardTitle;
	private String boardContent;
	private String boardRegdt;
	private int boardReadcount;
	private String memberName; //11/17추가
}
