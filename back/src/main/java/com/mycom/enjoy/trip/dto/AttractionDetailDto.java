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
public class AttractionDetailDto {
	private String contentId;
	private String title;
	private String addr1;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private String overview;
	private double latitude;
	private double longitude;
}
