package com.mycom.enjoy.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoy.trip.dao.AttractionDao;
import com.mycom.enjoy.trip.dto.AttractionDetailDto;
import com.mycom.enjoy.trip.dto.AttractionThumbnailDto;
import com.mycom.enjoy.trip.dto.CityDto;
import com.mycom.enjoy.trip.dto.RegionDto;
import com.mycom.enjoy.trip.dto.SearchParamDto;
import com.mycom.enjoy.trip.dto.SearchResultDto;

@Service
public class AttractionServiceImpl implements AttractionService{
	@Autowired
	AttractionDao dao;
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public List<CityDto> getCity() {
		return dao.getCity();
	}
	
	@Override
	public List<RegionDto> getRegion(int city) {
		return dao.getRegion(city);
	}
	
	@Override
	public SearchResultDto search(SearchParamDto searchParamDto) {
		SearchResultDto result = new SearchResultDto();
		try {
			int count = dao.attractionListTotalCount(searchParamDto);
			List<AttractionThumbnailDto> list = dao.search(searchParamDto);
			result.setList(list);
			result.setCount(count);
			result.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}

	@Override
	public AttractionDetailDto searchDetail(int contentId) {
		return dao.searchDetail(contentId);
	}

	@Override
	public List<AttractionThumbnailDto> searchPopularAttr() {
		return dao.searchPopularAttr();
	}

//	@Override
//	public List<AttractionThumbnailDto> searchPopularAttrByAge(int stAge, int edAge) {
//		return dao.searchPopularAttrByAge(stAge, edAge);
//	}
	@Override
	public List<AttractionThumbnailDto> searchPopularAttrByAge() {
		return dao.searchPopularAttrByAge();
	}

	@Override
	public List<AttractionThumbnailDto> searchPopularAttrByDay() {
		return dao.searchPopularAttrByDay();
	}
	
	
}
