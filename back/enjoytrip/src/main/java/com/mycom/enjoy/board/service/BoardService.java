package com.mycom.enjoy.board.service;

import java.util.List;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;

public interface BoardService {
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	BoardDto boardDetail(int boardId);
}
