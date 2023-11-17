package com.mycom.enjoy.trip.service;

import java.util.List;

import com.mycom.enjoy.trip.dto.AttractionDetailDto;
import com.mycom.enjoy.trip.dto.AttractionThumbnailDto;
import com.mycom.enjoy.trip.dto.CityDto;
import com.mycom.enjoy.trip.dto.RegionDto;

public interface AttractionService {
	List<CityDto> getCity();
	List<RegionDto> getRegion(int city);
//	List<AttractionThumbnailDto> searchCity(int city);
//	List<AttractionThumbnailDto> searchRegion(int city, int region);
	List<AttractionThumbnailDto> search(int city, int region);
	AttractionDetailDto searchDetail(int contentId);
	List<AttractionThumbnailDto> searchPopularAttr();
	List<AttractionThumbnailDto> searchPopularAttrByAge(int stAge,int edAge);
	List<AttractionThumbnailDto> searchPopularAttrByDay();
}
