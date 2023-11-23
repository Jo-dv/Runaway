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
public class AttractionThumbnailDto {
	private int contentId;
	private String title;
	private String firstImage;
	private String sidoName;
}