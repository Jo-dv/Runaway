package com.mycom.enjoy.board.service;

import java.util.List;

import com.mycom.enjoy.board.dto.BoardDto;

public interface BoardService {
	List<BoardDto> boardList();
	int boardRegister(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	BoardDto boardDetail(int boardId);
}
