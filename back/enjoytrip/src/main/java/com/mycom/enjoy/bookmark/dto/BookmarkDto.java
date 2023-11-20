package com.mycom.enjoy.bookmark.dto;

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
public class BookmarkDto {
	private int memberId;
	private int contentId;
	private String bookmarkRegdt;
}
