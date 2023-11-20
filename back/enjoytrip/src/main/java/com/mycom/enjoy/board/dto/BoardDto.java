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
<<<<<<< HEAD
	private String memberName; //11/17추가
=======
	 //11/17추가
	private String memberName;
	private boolean sameUser;
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
}
