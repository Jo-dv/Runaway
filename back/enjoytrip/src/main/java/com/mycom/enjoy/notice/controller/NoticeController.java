package com.mycom.enjoy.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.notice.dto.NoticeDto;
import com.mycom.enjoy.notice.service.NoticeService;

@RestController
public class NoticeController {
	private final NoticeService service;

	public NoticeController(NoticeService service) {
		this.service = service;
	}
	
//	noticeList
	@GetMapping("/notices")
	public List<NoticeDto> noticeList(){
		return service.noticeList();
	}
//	noticeRegister
	@PostMapping("/notices")
	public Map<String,String> noticeRegister(NoticeDto dto){
		Map<String,String> ans = new HashMap<>();
		int result = service.noticeRegister(dto);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	noticeUpdate
	@PutMapping("/notices")
	public Map<String,String> noticeUpdate(NoticeDto dto){
		Map<String,String> ans = new HashMap<>();
		int result =  service.noticeUpdate(dto);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	noticeDelete
	@DeleteMapping("/notices/{noticeId}")
	public Map<String,String> noticeDelete(@PathVariable int noticeId){
		Map<String,String> ans = new HashMap<>();
		int result =  service.noticeDelete(noticeId);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	noticeDetail
	@GetMapping("/notices/{noticeId}")
	public NoticeDto noticeDetail(@PathVariable int noticeId){
		return service.noticeDetail(noticeId);
	}
}
