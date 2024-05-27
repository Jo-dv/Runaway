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
public class BookmarkDto {  // 다른 검색 메서드와 마찬가지로 양식 통일
	private int contentId;
	private String title;
	private String firstImage;
	private int memberId;
	private String bookmarkRegdt;
}
