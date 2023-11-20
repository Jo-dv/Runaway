package com.mycom.enjoy.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;
<<<<<<< HEAD

public interface BoardService {
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	BoardDto boardDetail(int boardId);
=======
import com.mycom.enjoy.board.dto.BoardResultDto;

public interface BoardService {
	BoardResultDto boardDetail(BoardParamDto boardParamDto);

	BoardResultDto boardDelete(int boardId);

	BoardResultDto boardUpdate(BoardDto dto);

	BoardResultDto boardInsert(BoardDto dto);

	BoardResultDto boardList(BoardParamDto boardParamDto);

	// int boardListTotalCount();
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
}
