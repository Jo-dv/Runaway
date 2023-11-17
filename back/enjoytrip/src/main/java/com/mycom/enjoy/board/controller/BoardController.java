package com.mycom.enjoy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;
import com.mycom.enjoy.board.service.BoardService;

@RestController
public class BoardController {
	private final BoardService service;

	public BoardController(BoardService service) {
		this.service = service;
	}
	
//	boardList
	@GetMapping("/boards")
	public List<BoardDto> boardList(BoardParamDto dto){
		return service.boardList(dto);
	}
//	boardRegister
	@PostMapping("/boards")
	public Map<String,String> boardRegister(BoardDto dto){
		Map<String,String> ans = new HashMap<>();
		int result = service.boardInsert(dto);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	boardUpdate
	@PutMapping("/boards")
	public Map<String,String> boardUpdate(BoardDto dto){
		Map<String,String> ans = new HashMap<>();
		int result =  service.boardUpdate(dto);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	boardDelete
	@DeleteMapping("/boards/{boardId}")
	public Map<String,String> boardDelete(@PathVariable int boardId){
		Map<String,String> ans = new HashMap<>();
		int result =  service.boardDelete(boardId);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
	}
//	boardDetail
	@GetMapping("/boards/{boardId}")
	public BoardDto boardDetail(@PathVariable int boardId){
		BoardDto detail =service.boardDetail(boardId);
		return detail; 
	} 
}
