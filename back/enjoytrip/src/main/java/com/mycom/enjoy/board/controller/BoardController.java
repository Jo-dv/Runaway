package com.mycom.enjoy.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;
<<<<<<< HEAD
=======
import com.mycom.enjoy.board.dto.BoardResultDto;
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
import com.mycom.enjoy.board.service.BoardService;
import com.mycom.enjoy.member.dto.MemberDto;

@RestController
public class BoardController {
	private final BoardService service;

	public BoardController(BoardService service) {
		this.service = service;
	}
	private final int SUCCESS = 1;
	private final int FAIL = -1;
//	boardList
<<<<<<< HEAD
	@GetMapping("/boards")
	public List<BoardDto> boardList(BoardParamDto dto){
		return service.boardList(dto);
=======
	@GetMapping("/boards") // ok
	public BoardResultDto boardList(@ModelAttribute BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto;
		if (boardParamDto.getSearchWord() == null) { // isEmpty()
			boardResultDto = service.boardList(boardParamDto);
		} else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		return boardResultDto;
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
	}

//	boardRegister
	@PostMapping("/boards")
<<<<<<< HEAD
	public Map<String,String> boardRegister(BoardDto dto){
		Map<String,String> ans = new HashMap<>();
		int result = service.boardInsert(dto);
		if(result==1) {
			ans.put("result","success");
		}
		return ans;
=======
	public BoardResultDto boardInsert(@RequestBody BoardDto dto, HttpSession session) {
		MemberDto member = (MemberDto) session.getAttribute("memberDto");
		dto.setMemberId(member.getMemberId());
		BoardResultDto boardResultDto = service.boardInsert(dto);
		return boardResultDto;
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
	}

//	boardUpdate
	@PutMapping("/boards") // multipart랑 쓸 경우에는 put안됨. post로 줘야함.
	public BoardResultDto boardUpdate(@RequestBody BoardDto dto) {
		BoardResultDto boardResultDto = service.boardUpdate(dto);
		return boardResultDto;
	}

//	boardDelete
	@DeleteMapping("/boards/{boardId}")
	public BoardResultDto boardDelete(@PathVariable int boardId) {
		BoardResultDto boardResultDto = service.boardDelete(boardId);

		return boardResultDto;
	}

//	boardDetail
	// Detail을 보려면 Login해야함. -> front에서 접근 막을것.
	@GetMapping("/boards/{boardId}")
<<<<<<< HEAD
	public BoardDto boardDetail(@PathVariable int boardId){
		BoardDto detail =service.boardDetail(boardId);
		return detail; 
	} 
=======
	public BoardResultDto boardDetail(@PathVariable int boardId, HttpSession session) {
		BoardParamDto paramDto = new BoardParamDto();
		BoardResultDto boardResultDto = new BoardResultDto();
		MemberDto member = (MemberDto) session.getAttribute("memberDto");
		if (member != null) {
			paramDto.setMemberId(member.getMemberId());
			paramDto.setBoardId(boardId);
			boardResultDto = service.boardDetail(paramDto);
			if (member.getMemberId() == boardResultDto.getDto().getMemberId()) {
				boardResultDto.getDto().setSameUser(true);
			}
		}else {
			boardResultDto.setResult(FAIL);
		}

		
		return boardResultDto;
	}
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
}
