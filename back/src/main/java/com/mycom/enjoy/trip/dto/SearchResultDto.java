package com.mycom.enjoy.trip.dto;

import java.util.List;

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
public class SearchResultDto {
	private int result;
	private List<AttractionThumbnailDto> list;  // 매칭되는 아이템들
	private int count;  // 총건수
}
