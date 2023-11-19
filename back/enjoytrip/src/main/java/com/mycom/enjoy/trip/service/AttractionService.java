package com.mycom.enjoy.trip.service;

import java.util.List;

import com.mycom.enjoy.trip.dto.AttractionDetailDto;
import com.mycom.enjoy.trip.dto.AttractionThumbnailDto;
import com.mycom.enjoy.trip.dto.CityDto;
import com.mycom.enjoy.trip.dto.RegionDto;
import com.mycom.enjoy.trip.dto.SearchParamDto;
import com.mycom.enjoy.trip.dto.SearchResultDto;

public interface AttractionService {
	List<CityDto> getCity();
	List<RegionDto> getRegion(int city);
	SearchResultDto search(SearchParamDto searchParamDto);
	AttractionDetailDto searchDetail(int contentId);
	List<AttractionThumbnailDto> searchPopularAttr();
//	List<AttractionThumbnailDto> searchPopularAttrByAge(int stAge,int edAge);
	List<AttractionThumbnailDto> searchPopularAttrByAge();
	List<AttractionThumbnailDto> searchPopularAttrByDay();
}
