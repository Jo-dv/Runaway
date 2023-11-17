package com.mycom.enjoy.trip.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.trip.dto.AttractionDetailDto;
import com.mycom.enjoy.trip.dto.AttractionThumbnailDto;
import com.mycom.enjoy.trip.dto.CityDto;
import com.mycom.enjoy.trip.dto.RegionDto;
import com.mycom.enjoy.trip.service.AttractionService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttractionController {
	@Autowired
	AttractionService service;
	
	@GetMapping(value = "/trip")  // 도시 정보
	public List<CityDto> getCity() {
		List<CityDto> list = service.getCity();
		return list;
	}
	
	@PostMapping(value = "/trip/getRegion")  // 지역 정보
	public List<RegionDto> getRegion(@RequestBody int city) {
		List<RegionDto> list = service.getRegion(city);
		return list;
	}

	@PostMapping(value = "/trip/search")  // 일반 검색 결과
	public List<AttractionThumbnailDto> search(@RequestBody Map<String, Integer> params) {
		int city = params.get("city");
	    int region = params.get("region");
		List<AttractionThumbnailDto> list = service.search(city, region);
		return list;
	}
	
	@PostMapping(value = "/trip/searchDetail")  // 상세 결과
	public AttractionDetailDto searchDetail(@RequestBody int contentId) {
		AttractionDetailDto description = service.searchDetail(contentId);
		return description;
	}

	@GetMapping("/trip/searchPopular")
	public List<AttractionThumbnailDto> searchPopularAttr() {
		return service.searchPopularAttr();
	}
	
//	@GetMapping("/trip/searchPopularAge")
//	public List<AttractionThumbnailDto> searchPopularAttrByAge(HttpSession session) {
//		MemberDto dto = (MemberDto) session.getAttribute("memberDto");
//		int age = Integer.parseInt(dto.getMemberBirth().substring(0,4));
//		LocalDate now = LocalDate.now();
//        int stAge = ((now.getYear()-age)/10) * 10;
//		int edAge = stAge+9;
//		return service.searchPopularAttrByAge(stAge,edAge);
//	}
	@GetMapping("/trip/searchPopularAge")  // 단순 연령별 인기 여행지 -> 로그인 하지 않았을 경우, 해당 칸이 공란이 되는 것을 방지
	public List<AttractionThumbnailDto> searchPopularAttrByAge() {
		return service.searchPopularAttrByAge();
	}
	
	@GetMapping("/trip/searchPopularDay")
	public List<AttractionThumbnailDto> searchPopularAttrByDay(){
		return service.searchPopularAttrByDay();
	}
}
