package com.mycom.enjoy.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.enjoy.trip.dao.AttractionDao;
import com.mycom.enjoy.trip.dto.AttractionDetailDto;
import com.mycom.enjoy.trip.dto.AttractionThumbnailDto;
import com.mycom.enjoy.trip.dto.CityDto;
import com.mycom.enjoy.trip.dto.RegionDto;

@Service
public class AttractionServiceImpl implements AttractionService{
	@Autowired
	AttractionDao dao;
	
	@Override
	public List<CityDto> getCity() {
		return dao.getCity();
	}
	
	@Override
	public List<RegionDto> getRegion(int city) {
		return dao.getRegion(city);
	}
	
	@Override
	public List<AttractionThumbnailDto> search(int city, int region) {
		return dao.search(city, region);
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
